package com.example.ktrotest.di.data


import com.jaebin.what.data.msg.MsgRepository
import com.jaebin.what.data.msg.MsgRepositoryImpl
import com.jaebin.what.data.msg.remote.MsgRemoteDataSource
import com.jaebin.what.data.profile.ProfileRepository
import com.jaebin.what.data.profile.ProfileRepositoryImpl
import com.jaebin.what.data.profile.local.ProfileLocalDataSource
import com.jaebin.what.data.roomlist.RoomListRepository
import com.jaebin.what.data.roomlist.RoomListRepositoryImpl
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun providesProfileRepository(
        profileLocalDataSource: ProfileLocalDataSource
    ): ProfileRepository = ProfileRepositoryImpl(profileLocalDataSource)

    @Provides
    @Reusable
    fun providesRoomListRepository(
       roomListRemoteDataSource: RoomListRemoteDataSource
    ): RoomListRepository = RoomListRepositoryImpl(roomListRemoteDataSource)

    @Provides
    @Reusable
    fun providesMsgRepository(
        msgRemoteDataSource: MsgRemoteDataSource
    ): MsgRepository = MsgRepositoryImpl(msgRemoteDataSource)

}