package com.jaebin.what.data.profile.local

import android.content.Context
import android.content.SharedPreferences

class ProfileLocalDataSourceImpl(context: Context) :ProfileLocalDataSource {

    private var preferences: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    override fun saveProfile(key: String, str: String) {
        return preferences.edit().putString(key, str).apply()
    }

    override fun getProfile(key: String, defValue: String):String {
        return preferences.getString(key, defValue).toString()
    }



}