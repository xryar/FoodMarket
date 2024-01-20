package com.example.foodmarket

interface BasePresenter {
    //karena menggunakan rxjava jadi ada subscribe dan unsubscribe
    fun subscribe()
    fun unSubscribe()

}