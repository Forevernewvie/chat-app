package com.jaebin.what.signutil

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.jaebin.what.firebaseapi.Authentication.auth

class Login() {
    fun validateEmail(email:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}