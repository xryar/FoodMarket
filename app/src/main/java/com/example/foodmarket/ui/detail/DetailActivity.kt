package com.example.foodmarket.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.foodmarket.R
import com.example.foodmarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    fun toolbarPayment(){
        binding.includeToolbar.toolbar.visibility = View.VISIBLE
        binding.includeToolbar.toolbar.title = "Payment"
        binding.includeToolbar.toolbar.subtitle = "You deserve better meal"
        binding.includeToolbar.toolbar.navigationIcon = resources.getDrawable(
            R.drawable.ic_arrow_back, null
        )
        binding.includeToolbar.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun toolbarDetail(){
        binding.includeToolbar.toolbar.visibility = View.GONE
    }

}