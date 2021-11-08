package com.jaebin.what.data.roomlist

import com.jaebin.what.data.roomlist.remote.OnRoomInfoDataListener
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSourceImpl
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RoomListRepositoryImpl :RoomListRepository,KoinComponent{
    private val roomListRemoteDataSource: RoomListRemoteDataSourceImpl by inject()

    override fun fetchRoomInfoData(callback: OnDataListenSuccessOrFail<ChatRoomModel>) {
        roomListRemoteDataSource.fetchRoomInfoData(object : OnRoomInfoDataListener {
            override fun success(roomModel: ChatRoomModel) {
                callback.success(roomModel)
            }

            override fun fail(errMsg: String) {
                callback.fail(errMsg)
            }
        })
    }
}