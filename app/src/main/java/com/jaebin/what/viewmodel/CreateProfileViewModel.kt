package com.jaebin.what.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.ConstantsVal
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateProfileViewModel : ViewModel(),KoinComponent {
    private val profileLocalDataSource: ProfileLocalDataSourceImpl by inject()

    private val _nickName= MutableLiveData<String>()
    val nickName : LiveData<String>
        get() = _nickName


    init {
       _nickName.value= nickName.toString()
    }

     fun getNickName(){
        profileLocalDataSource.saveProfile(ConstantsVal.SHAREDPREFERENCES_KEY,_nickName.value!!)
    }
}