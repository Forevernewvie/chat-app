package com.jaebin.what.di.viewmodel

import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.ChatRoomListDataAdapter
import com.jaebin.what.viewmodel.ChatRoomListViewModel
import com.jaebin.what.viewmodel.ChatRoomViewModel
import com.jaebin.what.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val ViewModelModule = module {

   viewModel {HomeViewModel()}
   viewModel { ChatRoomViewModel() }
   viewModel {ChatRoomListViewModel()}
}