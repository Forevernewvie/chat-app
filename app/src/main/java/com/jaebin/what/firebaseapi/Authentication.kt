package com.jaebin.what.firebaseapi

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Authentication {
     val auth: FirebaseAuth = FirebaseAuth.getInstance()
     val mUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
}