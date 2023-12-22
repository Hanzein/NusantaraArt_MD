package com.capstone.bangkit.NusArt.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("error_message")
	val errorMessage: String,

	@field:SerializedName("data")
	val data: List<PredictData>,

	@field:SerializedName("error")
	val error: Boolean
)

