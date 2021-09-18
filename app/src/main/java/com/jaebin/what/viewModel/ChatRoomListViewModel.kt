package com.jaebin.what.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.model.ChatRoomModel

class ChatRoomListViewModel :ViewModel() {

<<<<<<< HEAD
    val _roomList = MutableLiveData<ArrayList<ChatRoomModel>>()
=======
    val roomList = MutableLiveData<ArrayList<ChatRoomModel>>()
>>>>>>> chat

    private var items = ArrayList<ChatRoomModel>()

    init {
<<<<<<< HEAD
        _roomList.value =items
=======
        roomList.value =items
>>>>>>> chat
    }

    fun addItem(roomInfo:ChatRoomModel){
        items.add(roomInfo)
<<<<<<< HEAD
        _roomList.value = items
    }

=======
        roomList.value = items
    }

    fun clear(){
        items.clear()
        roomList.value = items
    }


>>>>>>> chat
}