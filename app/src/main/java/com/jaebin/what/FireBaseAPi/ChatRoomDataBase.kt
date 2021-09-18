package com.jaebin.what.FireBaseAPi

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ChatRoomDataBase {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val chatRoomRef : DatabaseReference = database.getReference("chatRoomContent")
<<<<<<< HEAD


=======
>>>>>>> chat
}