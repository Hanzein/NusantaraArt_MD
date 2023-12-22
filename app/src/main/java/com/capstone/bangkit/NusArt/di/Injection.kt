package com.capstone.bangkit.NusArt.di

import android.content.Context
import com.capstone.bangkit.NusArt.data.UserRepository
import com.capstone.bangkit.NusArt.data.pref.UserPreference
import com.capstone.bangkit.NusArt.data.pref.dataStore
import com.capstone.bangkit.NusArt.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
    val pref = UserPreference.getInstance(context.dataStore)
    val user = runBlocking { pref.getSession().first() }
    val apiService = ApiConfig.getApiService(user.token)
        val apiML = ApiConfig.getApiML()
        return UserRepository.getInstance(apiService, pref, apiML)
}
}