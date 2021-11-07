package com.jaebin.what.signutil

import android.util.Patterns

class Login {
    fun validateEmail(email:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}