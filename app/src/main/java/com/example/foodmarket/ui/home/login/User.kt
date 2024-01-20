package com.example.foodmarket.ui.home.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("address")
    val address: String,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("created_at")
    val created_at: String,
    @Expose
    @SerializedName("current_team_id")
    val current_team_id: Any,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val email_verified_at: Any,
    @Expose
    @SerializedName("houseNumber")
    val houseNumber: Any,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @Expose
    @SerializedName("profile_photo_path")
    val profile_photo_path: Any,
    @Expose
    @SerializedName("profile_photo_url")
    val profile_photo_url: String,
    @Expose
    @SerializedName("roles")
    val roles: String,
    @Expose
    @SerializedName("two_factor_confirmed_at")
    val two_factor_confirmed_at: Any,
    @Expose
    @SerializedName("updated_at")
    val updated_at: String
)