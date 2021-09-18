package com.jaebin.what.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaebin.what.model.Msg

class ChatRoomViewModel : ViewModel() {
    val _msgList = MutableLiveData<ArrayList<Msg>>()

    private var items = ArrayList<Msg>()

    init {
        _msgList.value = items
    }

    fun addItem(msgContent: Msg){
            items.add(msgContent)
            _msgList.value = items
    }

    fun clear(){
        items.clear()
        _msgList.value = items
    }

}