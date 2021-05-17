package com.example.stalker.repository

import android.util.Log
import com.example.stalker.api.UserApiEndpoint
import com.example.stalker.api.models.User
import io.reactivex.rxjava3.core.Single

class UserDataRepository(private val userApi: UserApiEndpoint) {
    private var cachedUserList: List<User>? = null

    fun fetchUsers(): Single<List<User>> {
        return if (cachedUserList != null) {
            Log.d("FETCH_CACHE", "fetch from cache")
            Single.just(cachedUserList)
        } else {
            Log.d("FETCH_CACHE", "fetch and save user list")
            userApi.getUsers().map {
                it.results
            }.doOnEvent { list, error ->
                if (error == null) {
                    cachedUserList = list
                }
            }
        }
    }

    fun getUser(email: String): Single<User> {
        return cachedUserList?.find {
            it.email == email
        }?.let {
            Single.just(it)
        } ?: Single.error(Throwable("User not found"))
    }


}