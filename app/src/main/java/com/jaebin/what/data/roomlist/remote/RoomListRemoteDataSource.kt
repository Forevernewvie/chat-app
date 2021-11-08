package com.jaebin.what.data.roomlist.remote

import com.jaebin.what.model.ChatRoomModel

interface RoomListRemoteDataSource {
    fun fetchRoomInfoData(callback: OnRoomInfoDataListener)
}

interface OnRoomInfoDataListener{
    fun success(roomModel: ChatRoomModel)
    fun fail(errMsg:String)
}


