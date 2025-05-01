package com.savethekrakens.lab4_userdirectory

import android.app.Application
import com.savethekrakens.lab4_userdirectory.data.AppContainer
import com.savethekrakens.lab4_userdirectory.data.DefaultAppContainer

class UserDirectoryApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}