package com.capstone.bangkit.NusArt.data.remote.response

import com.google.gson.annotations.SerializedName

data class FileUploadResponse(
//    @field:SerializedName("error")
//    val error: Boolean,
//    @field:SerializedName("message")
//    val message: String
    val style: String,
    val genre: String,
    val image: String,
    val era: String
)