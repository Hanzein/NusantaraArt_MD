package com.capstone.bangkit.NusArt.view.login

import androidx.lifecycle.ViewModel
import com.capstone.bangkit.NusArt.data.UserRepository
import com.capstone.bangkit.NusArt.data.pref.UserModel

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun loginUser(email: String, password: String) = repository.loginUser(email, password)

    suspend fun saveSession(user: UserModel) {
        repository.saveSession(user)
    }
}
