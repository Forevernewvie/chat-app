package com.jaebin.what.viewmodel


import androidx.lifecycle.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.firebaseapi.ChatRoomDataBase
import com.jaebin.what.model.ChatRoomModel
import org.koin.core.component.KoinComponent

class ChatRoomListViewModel :ViewModel(),KoinComponent {

    val roomInfoData = MutableLiveData<ArrayList<ChatRoomModel>>()
    private var items = ArrayList<ChatRoomModel>()
    init {
        roomInfoData.value=items
    }

    fun addItem(roomInfo:ChatRoomModel){
        items.add(roomInfo)
        roomInfoData.value = items
    }
    fun clear(){
        items.clear()
        roomInfoData.value = items
    }

     fun getChatRoomList(){

         ChatRoomDataBase.chatRoomRef.addChildEventListener(object : ChildEventListener {
             override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                 for (data in snapshot.children) {
                     var testItem = data.getValue(ChatRoomModel::class.java)!!
                     addItem(testItem)
                 }
             }
             override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

             }
             override fun onChildRemoved(snapshot: DataSnapshot) {
             }
             override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

             }
             override fun onCancelled(error: DatabaseError) {

             }
         })
         clear()
     }

}