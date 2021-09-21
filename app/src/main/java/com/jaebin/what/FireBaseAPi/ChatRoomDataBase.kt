package com.jaebin.what.FireBaseAPi

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jaebin.what.KeyVariable.ref

object ChatRoomDataBase {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val chatRoomRef : DatabaseReference = database.getReference(ref)
}