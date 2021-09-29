package com.jaebin.what.fireBaseAPi

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jaebin.what.ConstantsVal.REF

object ChatRoomDataBase {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val chatRoomRef : DatabaseReference = database.getReference(REF)
}