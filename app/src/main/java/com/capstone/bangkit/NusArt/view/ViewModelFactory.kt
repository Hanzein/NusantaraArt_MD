package com.capstone.bangkit.NusArt.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.bangkit.NusArt.data.UserRepository
import com.capstone.bangkit.NusArt.di.Injection
import com.capstone.bangkit.NusArt.view.add.AddStoryViewModel
//import com.capstone.bangkit.NusArt.view.add.AddStoryViewModel
import com.capstone.bangkit.NusArt.view.fragment.home.HomeViewModel
import com.capstone.bangkit.NusArt.view.fragment.profile.ProfileViewModel
import com.capstone.bangkit.NusArt.view.login.LoginViewModel
//import com.capstone.bangkit.NusArt.view.fragment.map.MapViewModel
import com.capstone.bangkit.NusArt.view.signup.SignupViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also {
                instance = it
            }
    }

}