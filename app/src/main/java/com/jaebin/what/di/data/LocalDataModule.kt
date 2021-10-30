package com.jaebin.what.di

import com.jaebin.what.data.profile.ProfileRepository
import com.jaebin.what.data.profile.ProfileRepositoryImpl
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val LocalDataModule = module{

    single { ProfileRepositoryImpl() } bind ProfileRepository::class

    factory {
        ProfileLocalDataSourceImpl(androidContext())
    }

}