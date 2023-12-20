package com.capstone.bangkit.NusArt.view.add

import androidx.lifecycle.ViewModel
import com.capstone.bangkit.NusArt.data.UserRepository
import java.io.File


class AddStoryViewModel(private val repository: UserRepository) : ViewModel() {
    fun uploadImage(file: File) = repository.uploadImage(file)
}