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

    private val title = MutableLiveData<String>()
    val titleData:LiveData<String>
        get() = title

     var nickName = MutableLiveData<String>()
     var profileImg = MutableLiveData<String>()


    init {
        profileImg.value= profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,"")
        nickName.value = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        title.value="Jaebin Talk"
    }

    fun setHomeView(){
        nickName.value = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        profileImg.value= profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,"")
    }
}

