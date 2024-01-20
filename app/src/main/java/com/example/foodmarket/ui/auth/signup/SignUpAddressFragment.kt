package com.example.foodmarket.ui.auth.signup

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentSignUpAddressBinding
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.ui.MainActivity
import com.example.foodmarket.ui.auth.AuthActivity
import com.example.foodmarket.ui.home.login.LoginResponse
import com.google.gson.Gson


class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private lateinit var binding: FragmentSignUpAddressBinding

    private lateinit var data:RegisterRequest

    lateinit var presenter: SignUpPresenter

    var progressDialog: Dialog?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()
    }

    private fun initListener() {

        binding.btnSignUpNow.setOnClickListener {

            var phone = binding.edPhoneNumber.toString()
            var address = binding.edAddress.toString()
            var houseNumber = binding.edHouseNumber.toString()
            var city = binding.edCity.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            if (phone.isNullOrEmpty()){
                binding.edPhoneNumber.error = "Silahkan Masukkan Nomor Telepon Anda"
                binding.edPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()){
                binding.edAddress.error = "Silahkan Masukkan Alamat Anda"
                binding.edAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()){
                binding.edHouseNumber.error = "Silahkan Masukkan Nomor Rumah Anda"
                binding.edHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()){
                binding.edCity.error = "Silahkan Masukkan Kota Anda"
                binding.edCity.requestFocus()
            } else {
//                presenter.submitRegister(data, it)
                presenter.submitPhotoRegister(data.filePath!!, it)
            }

        }
    }

    private fun initDummy(){
        binding.edPhoneNumber.setText("085758145632")
        binding.edAddress.setText("Jalan Jendelan gajah")
        binding.edHouseNumber.setText("155")
        binding.edCity.setText("Depok")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null){
            Navigation.findNavController(view)
                .navigate(R.id.action_fragmentSignUpAddress_to_fragmentSignUpSuccess, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {

        }

    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_fragmentSignUpAddress_to_fragmentSignUpSuccess, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: List<String>) {
        Toast.makeText(activity, "Gagal Register Cik", Toast.LENGTH_LONG).show()
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