package com.jaebin.what.viewmodel


import androidx.lifecycle.*
import com.jaebin.what.data.roomlist.RoomListRepositoryImpl
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatRoomListViewModel :ViewModel(),KoinComponent {

    private val roomRemoteDataSource: RoomListRepositoryImpl by inject()

    val roomInfoData = MutableLiveData<ArrayList<ChatRoomModel>>()
    private var items = ArrayList<ChatRoomModel>()

    init {
        roomInfoData.postValue(items)
    }

    fun addItem(roomInfo:ChatRoomModel){
        items.add(roomInfo)
        roomInfoData.postValue(items)

    }
    fun clear(){
        items.clear()
        roomInfoData.postValue(items)
    }

    fun getChatRoomList(onFail:(String)->Unit){
        roomRemoteDataSource.fetchRoomInfoData(object : OnDataListenSuccessOrFail<ChatRoomModel> {
            override fun success(item: ChatRoomModel) {
                addItem(item)
            }

            override fun fail(errMsg: String) {
                onFail(errMsg)
                return
            }
        })
        clear()
    }

}