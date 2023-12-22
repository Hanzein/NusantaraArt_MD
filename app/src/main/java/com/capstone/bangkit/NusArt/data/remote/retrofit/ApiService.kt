package com.dicoding.picodiploma.storyapp.data.remote.retrofit

import com.capstone.bangkit.NusArt.data.remote.response.ArtResponse
import com.capstone.bangkit.NusArt.data.remote.response.PredictData
import com.capstone.bangkit.NusArt.data.remote.response.LoginResponse
import com.capstone.bangkit.NusArt.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @POST("users")
    suspend fun register(
        @Body requestBody: RequestBody
        ): RegisterResponse

    @POST("users/login")
    suspend fun login(
        @Body requestBody: RequestBody
    ): LoginResponse

    @GET("arts")
    suspend fun getArts(
    ): ArtResponse

    @Multipart
    @POST("arts")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): PredictData


    @GET("arts")
    suspend fun getArts(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): ArtResponse
}