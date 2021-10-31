package com.jaebin.what.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.onSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SingUpViewModel:ViewModel(),KoinComponent {

    private val loginUtil : Login by inject()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String>
        get() = _pwd



    fun signUp(callback:onSuccessOrFail){
        if (_email.value.toString() == "" || _pwd.value.toString() == ""){
            callback.onEmailOrPassWordERR()
            return
        }else if(!loginUtil.validateEmail(_email.value.toString())){
            callback.onInValidateEmail()
            return
        }else{
            Authentication.auth.createUserWithEmailAndPassword(_email.value.toString(),_pwd.value.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                    callback.onSuccess()
                    verifyEmail(callback)
                }else{
                   callback.onFail()
                }
            }
        }
    }


    private fun verifyEmail(callback:onSuccessOrFail){
        Authentication.auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener{
                if(it.isSuccessful){
                    callback.onSendEmail()
                }else{
                    callback.onInValidateEmail()
                    Log.d("verifyEmail", "verifyEmailErr: ${it.exception}")
                }
            }
    }

}