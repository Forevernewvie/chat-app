package com.example.ktrotest.di

import android.content.Context
import com.example.ktrotest.di.data.LocalModule
import com.example.ktrotest.di.data.RemoteModule
import com.example.ktrotest.di.data.RepositoryModule
import com.jaebin.what.di.data.PreferenceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// dagger2 위밋 앱 코드 참조 작성
// dagger2 자체(subComponet 생성시 그래프가 어디 밑으로 그려지는지) 및 어노테이션에 대한 이해 더 필요 ex) factory, binds 등

@Singleton
@Component(modules = [PreferenceModule::class,RemoteModule::class,RepositoryModule::class ,LocalModule::class,ViewModelModule::class,ViewModelFactoryModule::class,SubComponentModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun mainComponent() : MainComponent.Factory

}