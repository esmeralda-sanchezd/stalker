package com.example.stalker.ui.userlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stalker.api.models.User
import com.example.stalker.repository.UserDataRepository

class UserDetailsViewModel : ViewModel() {
    private var dataRepository: UserDataRepository? = null
    private val userLiveData: MutableLiveData<User> = MutableLiveData()
    
    fun observeLiveData() : LiveData<User> = userLiveData

    fun provideDataRepository(dataRepository: UserDataRepository){
        this.dataRepository = dataRepository
    }


    fun fetchUser(email: String){
        dataRepository?.getUser(email)?.subscribe({
            userLiveData.postValue(it)
        },{
            Log.e("FETCH_CACHE", "error fetching user view model", it);
        })

    }
}