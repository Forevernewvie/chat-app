package com.jaebin.what.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.OnSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SingUpViewModel:ViewModel(),KoinComponent {

    private val loginUtil : Login by inject()

   val _email = MutableLiveData<String>()
   val _pwd = MutableLiveData<String>()

    fun signUp(callback:OnSuccessOrFail){
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


    private fun verifyEmail(callback:OnSuccessOrFail){
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