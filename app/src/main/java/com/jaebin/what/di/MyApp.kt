package com.example.ktrotest.di

import android.app.Application

class MyApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}