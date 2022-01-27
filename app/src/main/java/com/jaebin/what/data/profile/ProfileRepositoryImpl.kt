package com.jaebin.what.data.profile

import com.jaebin.what.data.profile.local.ProfileLocalDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource
) : ProfileRepository  {



    override fun saveProfile(key: String, str: String) {
        profileLocalDataSource.saveProfile(key,str)
    }

    override fun getProfile(key: String, defValue: String): String {
        return profileLocalDataSource.getProfile(key,defValue)
    }
}