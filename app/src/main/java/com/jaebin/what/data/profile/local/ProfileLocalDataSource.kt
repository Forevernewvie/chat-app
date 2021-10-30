package com.jaebin.what.data.profile.local

interface ProfileLocalDataSource {
    fun saveProfile(key: String, str: String)
    fun getProfile(key: String, defValue: String):String
}