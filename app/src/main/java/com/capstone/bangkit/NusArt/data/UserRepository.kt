package com.capstone.bangkit.NusArt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.bangkit.NusArt.data.pref.UserModel
import com.capstone.bangkit.NusArt.data.pref.UserPreference
import com.capstone.bangkit.NusArt.data.remote.response.ArtResponse
import com.capstone.bangkit.NusArt.data.remote.response.AuthRequest
import com.capstone.bangkit.NusArt.data.remote.response.FileUploadResponse
import com.capstone.bangkit.NusArt.data.remote.response.ListArtItem
import com.capstone.bangkit.NusArt.data.remote.response.LoginResponse
import com.capstone.bangkit.NusArt.data.remote.response.RegisterResponse
import com.capstone.bangkit.NusArt.data.remote.retrofit.ApiConfig
import com.dicoding.picodiploma.storyapp.data.remote.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class UserRepository private constructor(

    private val apiService: ApiService,

    private val userPreference: UserPreference
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getStoryPaging(): LiveData<PagingData<ListArtItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(userPreference)
            }
        ).liveData
    }


    fun getStory() = liveData {
        emit(ResultState.Loading)

        try {
            val userModel = getSession().first()
            val token = userModel.token
            val successResponse = ApiConfig.getApiService(token).getArts()
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ArtResponse::class.java)
            emit(ResultState.Error(errorResponse.errorMessage.toString()))
        }
    }


//    fun getStoriesWithLocation() = liveData {
//        emit(ResultState.Loading)
//        try {
//            val userModel = getSession().first()
//            val token = userModel.token
//            val successResponse =
//                ApiConfig.getApiService(token).getStoriesWithLocation(location = 1)
//            emit(ResultState.Success(successResponse))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, ArtResponse::class.java)
//            emit(ResultState.Error(errorResponse.errorMessage.toString()))
//        }
//    }

    fun registerUser(email: String, password: String) = liveData {
        emit(ResultState.Loading)

        try {
            val gson = Gson()
            val data = AuthRequest(email,password)
            val request = gson.toJson(data).toRequestBody()
            val successResponse = apiService.register(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(ResultState.Error(errorResponse.errorMessage.toString()))
        }
    }

    fun loginUser(email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val gson = Gson()
            val data = AuthRequest(email,password)
            val request = gson.toJson(data).toRequestBody()
            val successResponse = apiService.login(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultState.Error(errorResponse.errorMessage.toString()))
        }
    }

    fun uploadImage(imageFile: File) = liveData {
        emit(ResultState.Loading)
        //val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        try {
            val userModel = getSession().first()
            val token = userModel.token
            val successResponse = FileUploadResponse(
                "Abad ke 16",
                "Realisme",
                "Sejarah",
                "https://i1.wp.com/lsfdiscourse.org/wp-content/uploads/2020/04/web-realisme-estetika.jpg?fit=1366%2C768&ssl=1",
            )
//            val successResponse =
//                ApiConfig.getApiService(token).uploadImage(multipartBody)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            emit(ResultState.Error(e.localizedMessage))
        }

    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}