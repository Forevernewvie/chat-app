package com.jaebin.what.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.ConstantsVal
import com.jaebin.what.ConstantsVal.DEFAULT_NICK_NAME
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.utils.BitmapUtil

class CreateProfileViewModel : ViewModel() {


    val _nickName= MutableLiveData<String>()
    val _profileImg = MutableLiveData<Bitmap>()


    init {
        if(_nickName.value.isNullOrEmpty()){
            _nickName.value= DEFAULT_NICK_NAME
        }else{
            _nickName.value=  profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        }
        setView()
    }

    fun setView() {
        _profileImg.value =
            profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY, "").let {
                bitMapUtil.stringToBitMap(it)
            }
    }

    fun saveImage(bitmap:Bitmap){
        profileLocalDataSource.saveProfile(ConstantsVal.SHAREDPREFERENCES_IMG_KEY,bitMapUtil.bitMapToString(bitmap))
    }


     fun saveNickName(){
         profileLocalDataSource.saveProfile(ConstantsVal.SHAREDPREFERENCES_KEY,_nickName.value!!)
    }

}