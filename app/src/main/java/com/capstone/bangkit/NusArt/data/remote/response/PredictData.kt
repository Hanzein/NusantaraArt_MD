package com.capstone.bangkit.NusArt.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictData(

    @field:SerializedName("genre")
    val genre: String,

    @field:SerializedName("era")
    val era: String,

    @field:SerializedName("style")
    val style: String,
    )