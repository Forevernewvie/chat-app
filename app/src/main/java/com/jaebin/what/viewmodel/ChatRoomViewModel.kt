package com.jaebin.what.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.jaebin.what.ConstantsVal
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.data.msg.MsgRepositoryImpl
import com.jaebin.what.data.profile.ProfileRepositoryImpl
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.firebaseapi.ChatDataBase
import com.jaebin.what.model.Msg
import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.ChatContentAdapter.Companion.IMAGE
import com.jaebin.what.utils.BitmapUtil
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import com.jaebin.what.utils.OnFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatRoomViewModel: ViewModel(),KoinComponent {

    private val msgRemoteDataSource : MsgRepositoryImpl by inject()
    private val profileLocalDataSource: ProfileRepositoryImpl by inject()
    private val bitMapUtil : BitmapUtil by inject()

    val chatContentData = MutableLiveData<ArrayList<Msg>>()
    private var items = ArrayList<Msg>()

    val _chatContent = MutableLiveData<String>()
    val _titleBar = MutableLiveData<String>()


    init {
        chatContentData.postValue(items)
    }

    fun addItem(msg:Msg){
        items.add(msg)
        chatContentData.postValue(items)
    }
    fun clear(){
        items.clear()
        chatContentData.postValue(items)
    }

    fun setTitleChatRoom(roomName:String,headCount:String){
        ChatDataBase.chatDataRef = ChatDataBase.database.getReference(roomName)
        _titleBar.value = String.format("$roomName ($headCount)")
    }

    fun setImgContent(bitmap:Bitmap){
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val nickName = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        val uID = Authentication.auth.uid.toString()
        ChatDataBase.chatDataRef.push().setValue(Msg(viewType = IMAGE,name=nickName,time=timeStamp,uid = uID,
            img = bitMapUtil.bitMapToString(bitmap!!)))
    }


    fun setChatContent(){
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val nickName = profileLocalDataSource.getProfile(ConstantsVal.SHAREDPREFERENCES_KEY,"")
        val uID = Authentication.auth.uid.toString()
        val msgData = Msg(ChatContentAdapter.MSG,nickName,_chatContent.value,timeStamp,uID)
        ChatDataBase.chatDataRef.push().setValue(msgData)
    }


    fun getMsgList(callback: OnFail){
        msgRemoteDataSource.fetchMsgData(object:OnDataListenSuccessOrFail<Msg>{
            override fun success(item: Msg) {
               addItem(item)
            }

            override fun fail(errMsg: String) {
                callback.onFail(errMsg)
            }
        })
        clear()
    }

}