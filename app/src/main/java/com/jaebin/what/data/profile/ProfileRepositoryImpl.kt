package com.jaebin.what.data.profile

import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileRepositoryImpl : ProfileRepository,KoinComponent {

    private val profileLocalDataSource: ProfileLocalDataSourceImpl by inject()

    override fun saveProfile(key: String, str: String) {
        profileLocalDataSource.saveProfile(key,str)
    }

    override fun getProfile(key: String, defValue: String): String {
        return profileLocalDataSource.getProfile(key,defValue)
    }
}