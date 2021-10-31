package com.jaebin.what.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.onSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FindPwdViewModel:ViewModel(),KoinComponent {
    private val loginUtil : Login by inject()

    private val _email= MutableLiveData<String>()
    val email : LiveData<String>
        get() = _email

    private fun resetPassword(callback:onSuccessOrFail){
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

    fun findPassword(callback:onSuccessOrFail){
        if(loginUtil.validateEmail(_email.value.toString())){
            resetPassword(callback)
        }else{
            callback.onInValidateEmail()
        }
    }


}