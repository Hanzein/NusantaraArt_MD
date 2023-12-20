package com.capstone.bangkit.NusArt.view.signup

import androidx.lifecycle.ViewModel
import com.capstone.bangkit.NusArt.data.UserRepository

class SignupViewModel(private val repository: UserRepository) : ViewModel() {
    fun registerUser(email: String, password: String) =
        repository.registerUser(email, password)

}