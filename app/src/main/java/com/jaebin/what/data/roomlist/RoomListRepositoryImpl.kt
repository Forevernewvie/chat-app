package com.jaebin.what.data.roomlist

import com.jaebin.what.data.msg.remote.MsgRemoteDataSource
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSource
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSourceImpl
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class RoomListRepositoryImpl @Inject constructor(
    private val roomListRemoteDataSource: RoomListRemoteDataSource
) : RoomListRepository{

    override fun fetchRoomInfoData() {
        roomListRemoteDataSource.fetchRoomInfoData().observeOn(Schedulers.io())
    }
}