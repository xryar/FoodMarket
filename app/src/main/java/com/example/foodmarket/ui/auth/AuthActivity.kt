package com.example.foodmarket.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.foodmarket.R
import com.example.foodmarket.databinding.ActivityAuthBinding
import com.example.foodmarket.databinding.LayoutToolbarBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2){
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()

            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_fragmentSignIn_to_fragmentSignUp, null, navOptions)
        }
    }

    fun toolbarSignUp(){
        binding.toolbarInclude.toolbar.title = "Sign Up"
        binding.toolbarInclude.toolbar.subtitle ="Register and Eat"
        binding.toolbarInclude.toolbar.navigationIcon = resources.getDrawable(
            R.drawable.ic_arrow_back, null
        )
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarSignUpAddress(){
        binding.toolbarInclude.toolbar.title = "Address"
        binding.toolbarInclude.toolbar.subtitle ="Make sure it's valid"
        binding.toolbarInclude.toolbar.navigationIcon = resources.getDrawable(
            R.drawable.ic_arrow_back, null
        )
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarSignUpSuccess(){
        binding.toolbarInclude.toolbar.visibility = View.GONE
    }

}


