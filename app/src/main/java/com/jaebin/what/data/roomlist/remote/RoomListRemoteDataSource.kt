package com.jaebin.what.data.roomlist.remote

import com.jaebin.what.model.ChatRoomModel
import io.reactivex.rxjava3.core.Flowable

interface RoomListRemoteDataSource {
    fun fetchRoomInfoData() : Flowable<List<ChatRoomModel>>
}



