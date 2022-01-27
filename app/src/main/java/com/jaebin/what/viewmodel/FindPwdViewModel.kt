package com.jaebin.what.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.OnSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FindPwdViewModel:ViewModel() {


    val _email= MutableLiveData<String>()

    private fun resetPassword(callback:OnSuccessOrFail){
        Authentication.auth.sendPasswordResetEmail(_email.value.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                callback.onSendEmail()
                callback.onSuccess()
            }
            else{
                callback.onFail()
            }
        }
    }

    fun findPassword(callback:OnSuccessOrFail){
        if(loginUtil.validateEmail(_email.value.toString())){
            resetPassword(callback)
        }else{
            callback.onInValidateEmail()
        }
    }


}