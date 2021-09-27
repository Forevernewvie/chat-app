package com.jaebin.what.FireBaseAPi

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ChatDataBase {

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    var chatDataRef : DatabaseReference = database.getReference()
}