package com.jaebin.what.fireBaseAPi

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jaebin.what.signUtil.Basic
import com.jaebin.what.signUtil.GoogleLogin

object Authentication {
     val auth: FirebaseAuth = FirebaseAuth.getInstance()
     val mUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
     val signUtil = Basic()
     val googleUtil = GoogleLogin()
}