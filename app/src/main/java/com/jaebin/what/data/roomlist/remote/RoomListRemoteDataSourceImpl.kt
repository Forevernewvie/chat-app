package com.jaebin.what.data.roomlist.remote

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.jaebin.what.model.ChatRoomModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class RoomListRemoteDataSourceImpl @Inject constructor(
    private val database: DatabaseReference
) :RoomListRemoteDataSource{


    override fun fetchRoomInfoData(): Flowable<List<ChatRoomModel>> {
        return Flowable.create({
                emitter->
            val dataListener = object :  ChildEventListener{
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val chatRoomList = snapshot.getValue(ChatRoomModel::class.java)!!
                    emitter.onNext(listOf(chatRoomList))
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
            database.addChildEventListener(dataListener)

        }, BackpressureStrategy.BUFFER)
    }

}