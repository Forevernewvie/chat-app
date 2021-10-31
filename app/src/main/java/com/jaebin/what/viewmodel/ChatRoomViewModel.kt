package com.jaebin.what.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.ConstantsVal
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.firebaseapi.ChatDataBase
import com.jaebin.what.firebaseapi.ChatRoomDataBase
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.model.Msg
import com.jaebin.what.recyclerView.ChatContentAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
class ChatRoomViewModel() : ViewModel(),KoinComponent {

    private val profileLocalDataSource: ProfileLocalDataSourceImpl by inject()

    val chatContentData = MutableLiveData<ArrayList<Msg>>()
    private val items = ArrayList<Msg>()

    private val _chatContent = MutableLiveData<String>()
    val chatContent:LiveData<String>
        get() = _chatContent

    private val _titleBar = MutableLiveData<String>()
    val titleBar : LiveData<String>
        get() = _titleBar

    init {
        chatContentData.postValue(items)
    }

    fun addItem(roomInfo:Msg){
        items.add(roomInfo)
        chatContentData.postValue(items)
    }
    fun clear(){
        items.clear()
        chatContentData.value = items
    }

    fun setTitleChatRoom(roomName:String,headCount:String){
        ChatDataBase.chatDataRef = ChatDataBase.database.getReference(roomName)
        _titleBar.value = String.format("$roomName ($headCount)")
    }


    fun setChatContent(){
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val nickName = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        val uID = Authentication.auth.uid.toString()
        val msgData = Msg(ChatContentAdapter.MSG,nickName,_chatContent.value,timeStamp,uID)
        ChatDataBase.chatDataRef.push().setValue(msgData)
    }

    fun getChatContentList(){
        clear()
        ChatDataBase.chatDataRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    addItem( snapshot.getValue(Msg::class.java)!!)
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

    }

}