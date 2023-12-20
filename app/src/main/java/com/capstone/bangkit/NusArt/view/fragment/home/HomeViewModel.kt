package com.capstone.bangkit.NusArt.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.bangkit.NusArt.data.UserRepository
import com.capstone.bangkit.NusArt.data.pref.UserModel
import com.capstone.bangkit.NusArt.data.remote.response.ListArtItem

class HomeViewModel (private val repository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    val repo: LiveData<PagingData<ListArtItem>> =
        repository.getStoryPaging().cachedIn(viewModelScope)

//    fun logout() {
//        viewModelScope.launch {
//            repository.logout()
//        }
//    }

    fun getStory() = repository.getStory()
}