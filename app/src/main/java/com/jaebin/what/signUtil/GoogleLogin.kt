package com.jaebin.what.signUtil

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.jaebin.what.R
import com.jaebin.what.fireBaseAPi.Authentication

class GoogleLogin {

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        Authentication.auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GooGle", "signInWithCredential:success")
                    val user = Authentication.auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GooGle", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun getGso(context: Context): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context?.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }



}