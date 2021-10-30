package com.jaebin.what.data.profile

interface ProfileRepository {
    fun saveProfile(key: String, str: String)
    fun getProfile(key: String, defValue: String):String
}