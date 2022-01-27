package com.jaebin.what.data.msg.remote

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.jaebin.what.model.Msg
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class MsgRemoteDataSourceImpl @Inject constructor(
    private val database: DatabaseReference
) : MsgRemoteDataSource{

    override fun fetchMsgData(): Flowable<List<Msg>> {
       return Flowable.create({
           emitter->
           val msgListener = object :  ChildEventListener{
               override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                   val msgList = snapshot.getValue(Msg::class.java)!!
                   emitter.onNext(listOf(msgList))
               }

               override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
               }

               override fun onChildRemoved(snapshot: DataSnapshot) {
               }

               override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
               }

               override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
               }

           }
           database.addChildEventListener(msgListener)

       },BackpressureStrategy.BUFFER)
    }

}