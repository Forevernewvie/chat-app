package com.example.ktrotest.di.data


import android.content.SharedPreferences
import com.jaebin.what.data.profile.local.ProfileLocalDataSource
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesProfileLocalDataSource(preferences: SharedPreferences): ProfileLocalDataSource{
        return ProfileLocalDataSourceImpl(preferences)
    }

}