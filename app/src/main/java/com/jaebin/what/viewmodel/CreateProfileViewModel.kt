package com.jaebin.what.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.ConstantsVal
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.utils.BitmapUtil
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateProfileViewModel : ViewModel(),KoinComponent {
    private val profileLocalDataSource: ProfileLocalDataSourceImpl by inject()
    private val bitMapUtil : BitmapUtil by inject()

    val _nickName= MutableLiveData<String>()
    val _profileImg = MutableLiveData<Bitmap>()



    init {
       _nickName.value= "Defalut"
    }

    fun saveImage(bitmap:Bitmap){
        profileLocalDataSource.saveProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,bitMapUtil.bitMapToString(bitmap))
    }

     fun getNickName(){
         profileLocalDataSource.saveProfile(ConstantsVal.SHAREDPREFERENCES_KEY,_nickName.value!!)
    }
}