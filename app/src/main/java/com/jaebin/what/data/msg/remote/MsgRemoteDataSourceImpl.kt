package com.jaebin.what.data.msg.remote

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.ConstantsVal.FIREBASE_ERR_MSG
import com.jaebin.what.firebaseapi.ChatDataBase
import com.jaebin.what.model.Msg

class MsgRemoteDataSourceImpl : MsgRemoteDataSource{
    override fun fetchMsgData(callback: OnMsgDataListener) {

        ChatDataBase.chatDataRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
               callback.success(snapshot.getValue(Msg::class.java)!!)

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