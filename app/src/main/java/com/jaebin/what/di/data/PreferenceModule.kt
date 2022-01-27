package com.jaebin.what.di.data

import android.content.Context
import android.content.SharedPreferences
import com.jaebin.what.ConstantsVal
import com.jaebin.what.utils.BitmapUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    fun provideSharedPreference(context: Context) : SharedPreferences =
        context.getSharedPreferences(ConstantsVal.SHAREDPREFERENCES_KEY,Context.MODE_PRIVATE)

    /*
    @Singleton
    @Provides
    fun providePreferenceManager(
        context: Context
    ) : SharedPreferencesManager = SharedPreferencesManager(context)
    */

}