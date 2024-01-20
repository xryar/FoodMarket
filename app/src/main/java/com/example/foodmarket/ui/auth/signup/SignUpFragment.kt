package com.example.foodmarket.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentSignUpBinding
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    var filePath: Uri ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()


    }

    private fun initListener() {
        binding.imgProfile.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        binding.btnContinue.setOnClickListener{

            var fullname = binding.edFullName.text.toString()
            var email = binding.edEmail.text.toString()
            var password = binding.edPassword.text.toString()

            if (fullname.isNullOrEmpty()) {
                binding.edFullName.error = "Silahkan masukkan Nama Anda"
                binding.edFullName.requestFocus()
            } else if (email.isNullOrEmpty()) {
                binding.edEmail.error = "Silahkan masukkan Email Anda"
                binding.edEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                binding.edPassword.error = "Silahkan masukkan Password Anda"
                binding.edPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    fullname,
                    email,
                    password,
                    password,
                    "", "", "", "",
                    filePath
                )

                var bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_fragmentSignUp_to_fragmentSignUpAddress, bundle)
                (activity as AuthActivity).toolbarSignUpAddress()
            }
        }
    }

    private fun initDummy() {
        binding.edFullName.setText("Jennie Kim White")
        binding.edEmail.setText("jennie.kim@blackpink.com")
        binding.edPassword.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply (RequestOptions.circleCropTransform())
                .into(binding.imgProfile)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}