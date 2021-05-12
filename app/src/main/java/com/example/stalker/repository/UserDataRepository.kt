package com.example.stalker.repository

import com.example.stalker.api.UserApiEndpoint

class UserDataRepository(private val userApi: UserApiEndpoint) {
    fun fetchUsers()= userApi.getUsers()
}