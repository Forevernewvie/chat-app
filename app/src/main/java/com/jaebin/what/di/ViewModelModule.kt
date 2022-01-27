package com.example.ktrotest.di

import androidx.lifecycle.ViewModel
import com.jaebin.what.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModels(mainViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModels(homeViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SingUpViewModel::class)
    abstract fun bindSingUpViewModels(singUpViewModel: SingUpViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FindPwdViewModel::class)
    abstract fun bindFindPwdViewModels(findPwdViewModel: FindPwdViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateProfileViewModel::class)
    abstract fun bindCreateProfileViewModels(createProfileViewModel: CreateProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatRoomListViewModel::class)
    abstract fun bindChatRoomListViewModels(chatRoomListViewModel: ChatRoomListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatRoomViewModel::class)
    abstract fun bindChatRoomViewModel(chatRoomViewModel: ChatRoomViewModel) : ViewModel

}