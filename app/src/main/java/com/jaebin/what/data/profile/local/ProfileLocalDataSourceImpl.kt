package com.jaebin.what.data.profile.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) :ProfileLocalDataSource {

    override fun saveProfile(key: String, str: String) {
        return preferences.edit().putString(key, str).apply()
    }

    override fun getProfile(key: String, defValue: String):String {
        return preferences.getString(key, defValue).toString()
    }



}