package com.example.stalker.ui.userlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stalker.api.models.User
import com.example.stalker.repository.UserDataRepository

class UserViewModel: ViewModel() {
    private var dataRepository: UserDataRepository? = null
    private var users = MutableLiveData<List<User>>()

    fun provideDataRepository(dataRepository: UserDataRepository){
        this.dataRepository = dataRepository
        dataRepository.fetchUsers().subscribe({
            users.postValue(it.results)
        }, {
            Log.e("DEBUG_ERROR", "error", it)
        })
    }

    fun observeLiveData(): LiveData<List<User>> = users
}