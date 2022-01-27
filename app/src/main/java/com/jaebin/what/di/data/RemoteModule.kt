package com.example.ktrotest.di.data


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jaebin.what.data.msg.remote.MsgRemoteDataSource
import com.jaebin.what.data.msg.remote.MsgRemoteDataSourceImpl
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSource
import com.jaebin.what.data.roomlist.remote.RoomListRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesMsgRemoteDataSource(database: DatabaseReference) : MsgRemoteDataSource {
        return MsgRemoteDataSourceImpl(database)
    }

    @Singleton
    @Provides
    fun providesRoomListRemoteDataSource(database: DatabaseReference) : RoomListRemoteDataSource {
        return RoomListRemoteDataSourceImpl(database)
    }

    @Singleton
    @Provides
    fun providesFireDataBase() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun providesDataBase(firebaseDatabase: FirebaseDatabase) : DatabaseReference {
        return firebaseDatabase.reference
    }

}