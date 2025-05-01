package com.savethekrakens.lab4_userdirectory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.savethekrakens.lab4_userdirectory.UserDirectoryApplication
import com.savethekrakens.lab4_userdirectory.data.UserDataRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UserUiState{
    data class Success(val users: String): UserUiState
    object Error: UserUiState
    object Loading: UserUiState
}

class UserViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    init {
        getUsersInfo()
    }

    fun getUsersInfo(){
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                val listResult = userDataRepository.getResults()
                UserUiState.Success(
                    "Success ${listResult} users retrieved"
                )
            } catch (e: IOException){
                UserUiState.Error
            } catch (e: HttpException){
                UserUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserDirectoryApplication)
                val userDataRepository = application.container.userDataRepository
                UserViewModel(userDataRepository = userDataRepository)
            }
        }
    }

}