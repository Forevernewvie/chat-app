package com.jaebin.what.di

import android.app.Application
import com.jaebin.what.di.*
import com.jaebin.what.di.viewmodel.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartKoin : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@StartKoin)
            modules(listOf(LocalDataModule, AppModule,UtilsModule,SignUtilsModule,AdapterModule,
                RemoteDataModule, ViewModelModule))
        }
    }

}