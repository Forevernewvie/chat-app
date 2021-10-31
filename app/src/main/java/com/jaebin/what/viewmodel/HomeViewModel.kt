package com.jaebin.what.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.ConstantsVal
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel :ViewModel(),KoinComponent{
    private val profileLocalDataSource: ProfileLocalDataSourceImpl by inject()


    val _nickName = MutableLiveData<String>()
    val _profileImg = MutableLiveData<String>()

    init {
        _profileImg.value= profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,"")
        _nickName.value = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
    }

    fun setHomeView(){
        _nickName.value = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        _profileImg.value= profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,"")
    }
}

