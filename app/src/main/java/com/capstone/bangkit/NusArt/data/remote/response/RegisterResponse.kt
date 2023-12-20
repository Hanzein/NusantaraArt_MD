package com.capstone.bangkit.NusArt.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("error_message")
	val errorMessage: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
)

data class DataItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("domicile")
	val domicile: Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fullname")
	val fullname: Any? = null,

	@field:SerializedName("email")
	val email: String? = null
)
