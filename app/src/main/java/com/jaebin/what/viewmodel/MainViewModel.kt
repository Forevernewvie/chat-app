package com.jaebin.what.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.onSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel :ViewModel(),KoinComponent {

    private val loginUtil : Login by inject()

    val _email = MutableLiveData<String>()
    val _pwd = MutableLiveData<String>()

    private fun singIn(callback:onSuccessOrFail){

        if (_email.value.toString() == "" || _pwd.value.toString() == ""){
            callback.onEmailOrPassWordERR()
            return
        } else{
            Authentication.auth.signInWithEmailAndPassword(_email.value.toString(),_pwd.value.toString()).addOnCompleteListener {
                if (it.isSuccessful && Authentication.auth.currentUser?.isEmailVerified == true){
                    callback.onSuccess()
                }else{
                    callback.onFail()
                }
            }
        }
    }


    fun login(callback:onSuccessOrFail){
        if(loginUtil.validateEmail(_email.value.toString())){
            singIn(callback)
        }else{
            callback.onInValidateEmail()
            return
        }
    }

}