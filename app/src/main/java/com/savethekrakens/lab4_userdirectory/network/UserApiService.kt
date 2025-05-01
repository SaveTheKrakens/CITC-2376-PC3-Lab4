package com.savethekrakens.lab4_userdirectory.network

import com.savethekrakens.lab4_userdirectory.model.Results
import retrofit2.http.GET

interface UserApiService{
    @GET("/api/?results=20")
    suspend fun getResults(): Results
}