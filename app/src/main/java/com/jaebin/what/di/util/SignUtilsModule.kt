package com.jaebin.what.di

import com.jaebin.what.signutil.GoogleLogin
import com.jaebin.what.signutil.Login
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val SignUtilsModule = module {
    single  { Login() }
    single { GoogleLogin()}
}