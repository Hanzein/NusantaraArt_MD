package com.dicoding.picodiploma.storyapp.data.remote.retrofit

import com.capstone.bangkit.NusArt.data.remote.response.PredictData
import com.capstone.bangkit.NusArt.data.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiML {
    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): PredictResponse
}