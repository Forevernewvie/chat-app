package com.jaebin.what.data.roomlist.remote

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.ConstantsVal.FIREBASE_ERR_MSG
import com.jaebin.what.firebaseapi.ChatRoomDataBase
import com.jaebin.what.model.ChatRoomModel

class RoomListRemoteDataSourceImpl :RoomListRemoteDataSource{

    override fun fetchRoomInfoData(callback: OnRoomInfoDataListener) {
        ChatRoomDataBase.chatRoomRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                for (data in snapshot.children) {
                    callback.success( roomModel = data.getValue(ChatRoomModel::class.java)!!)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }
            override fun onCancelled(error: DatabaseError) {
                callback.fail(FIREBASE_ERR_MSG)
            }
        })

    }

}