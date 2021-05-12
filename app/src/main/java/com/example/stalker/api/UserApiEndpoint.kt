package com.example.stalker.api

import com.example.stalker.api.models.UserResults
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserApiEndpoint {

    @GET("/api/?results=25")
    fun getUsers(): Single<UserResults>
}