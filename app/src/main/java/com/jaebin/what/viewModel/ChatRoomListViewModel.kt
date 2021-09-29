package com.jaebin.what.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.model.ChatRoomModel

class ChatRoomListViewModel :ViewModel() {

    val roomList = MutableLiveData<ArrayList<ChatRoomModel>>()
    private var items = ArrayList<ChatRoomModel>()

    init {
        roomList.value =items
    }

    fun addItem(roomInfo:ChatRoomModel){
        items.add(roomInfo)
        roomList.value = items
    }

    fun deleteItem(pos:Int){
        items.removeAt(pos)
        roomList.value = items
    }



    fun clear(){
        items.clear()
        roomList.value = items
    }


}