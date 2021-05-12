package com.example.stalker

import android.app.Application
import com.example.stalker.api.UserApiEndpoint
import com.example.stalker.repository.UserDataRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StalkerApplication : Application() {
    private lateinit var dataRepository : UserDataRepository
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
        dataRepository = UserDataRepository(userApi)
    }


    fun getUserRepository() = dataRepository
}