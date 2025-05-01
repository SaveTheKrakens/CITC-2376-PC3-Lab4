package com.savethekrakens.lab4_userdirectory.data

import com.savethekrakens.lab4_userdirectory.model.Results
import com.savethekrakens.lab4_userdirectory.model.User
import com.savethekrakens.lab4_userdirectory.network.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

interface UserDataRepository{
    suspend fun getResults(): Results
}

class NetworkUserDataRepository(
    private val userApiService: UserApiService
) : UserDataRepository{
    override suspend fun getResults(): Results = withContext(Dispatchers.IO) {
        try {
            userApiService.getResults()
        }catch (e: IOException){
            throw Exception("Network Error", e)
        }catch (e: Exception){
            throw Exception("Unexpected Error", e)
        }
    }
}