package com.example.foodmarket.ui.auth.signin

import com.example.foodmarket.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.ui.home.login.LoginResponse

interface SignInContract {

    interface View: BaseView {

        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: List<String>)

    }

    interface Presenter : SignInContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }

}