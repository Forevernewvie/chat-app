package com.jaebin.what.di


import com.jaebin.what.data.msg.MsgRepository
import com.jaebin.what.data.msg.MsgRepositoryImpl
import com.jaebin.what.data.msg.remote.MsgRemoteDataSourceImpl
import com.jaebin.what.data.roomlist.RoomListRepository
import com.jaebin.what.data.roomlist.RoomListRepositoryImpl
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val RemoteDataModule = module {
    single { RoomListRepositoryImpl() } bind RoomListRepository::class
    single { MsgRepositoryImpl() } bind MsgRepository::class

    factory {
        RoomListRemoteDataSourceImpl()
    }

    factory {
        MsgRemoteDataSourceImpl()
    }

}