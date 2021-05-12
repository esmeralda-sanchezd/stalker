package com.example.stalker

import android.app.Application
import com.example.stalker.api.UserApiEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StalkerApplication : Application() {
    private lateinit var userApi: UserApiEndpoint

    override fun onCreate() {
        super.onCreate()
        val gson = GsonBuilder()
                .setLenient()
                .create()

       val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

        userApi = retrofit.create(UserApiEndpoint::class.java)
    }

    fun getUserApi() = userApi
}