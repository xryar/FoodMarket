package com.example.foodmarket.network

import android.util.Log
import com.example.foodmarket.BuildConfig
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.utils.Helpers
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client:Retrofit? = null
    private var endpoint:EndPoint? = null

    companion object{
        private val mInstance:HttpClient = HttpClient()

        fun getInstance():HttpClient{
            return mInstance
        }

    }

    init {
        buildRetrofitClient()
    }

    fun getApi() : EndPoint? {
        if (endpoint == null){
            endpoint = client!!.create(EndPoint::class.java)
        }
        return endpoint
    }

    private fun  buildRetrofitClient() {
        val token = FoodMarket.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token: String?){
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG){
            val  interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(FoodMarket.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "Bearer ${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL+"api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        endpoint = null

        if (token != null) {
            Log.v("tamvan", token)
        }

    }

    private fun getInterceptorWithHeader(headerName : String, headerValue : String) : Interceptor{
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptorWithHeader(header)
    }

    private fun getInterceptorWithHeader(headers : Map<String, String>) : Interceptor{
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value ) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method(), original.body())
            it.proceed(builder.build())
        }
    }

}