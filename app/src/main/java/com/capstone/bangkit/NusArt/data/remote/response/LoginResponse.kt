package com.capstone.bangkit.NusArt.data.remote.response

import android.util.Log
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("error_message")
	val errorMessage: String? = null,

	@field:SerializedName("data")
	val data : LoginResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class LoginResult(

	@field:SerializedName("access_token")
	val accessToken: String? = null
)
