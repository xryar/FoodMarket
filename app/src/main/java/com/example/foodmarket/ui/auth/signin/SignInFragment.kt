package com.example.foodmarket.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentSignInBinding
import com.example.foodmarket.ui.home.login.LoginResponse
import com.example.foodmarket.ui.MainActivity
import com.example.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson


class SignInFragment : Fragment(), SignInContract.View {

    private lateinit var binding: FragmentSignInBinding

    lateinit var presenter: SignInPresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignInPresenter(this)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        initDummy()
        initView()

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(activity, AuthActivity::class.java)
            intent.putExtra("page_request", 2)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {

            var email = binding.edEmail.text.toString()
            var password = binding.edPassword.text.toString()

            if (email.isEmpty()){
                binding.edEmail.error = "Masukkan Email dulu Cik"
                binding.edEmail.requestFocus()
            }else if (password.isEmpty()){
                binding.edPassword.error = "Masukkan Password dulu Cik"
                binding.edPassword.requestFocus()
            }else{
                presenter.submitLogin(email, password)
            }
        }

    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onLoginFailed(message: List<String>) {
        Toast.makeText(activity, "Gagal Masuk Cik", Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        binding.edEmail.setText("jennie.kim@blackpink.com")
        binding.edPassword.setText("12345678")
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}