package com.jaebin.what.di

import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.ChatRoomListDataAdapter
import org.koin.dsl.module

val AdapterModule = module {

    factory { ChatRoomListDataAdapter() }
    factory {ChatContentAdapter()}
}