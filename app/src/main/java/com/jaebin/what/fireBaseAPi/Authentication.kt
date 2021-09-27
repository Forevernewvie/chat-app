package com.jaebin.what.FireBaseAPi

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jaebin.what.signUtil.Basic

object Authentication {
     val auth: FirebaseAuth = FirebaseAuth.getInstance()

     val mUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
     val signUtil = Basic()
}