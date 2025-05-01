package com.savethekrakens.lab4_userdirectory.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.savethekrakens.lab4_userdirectory.network.UserApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userDataRepository: UserDataRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://randomuser.me/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userDataRepository: UserDataRepository by lazy {
        NetworkUserDataRepository(retrofitService)
    }
}