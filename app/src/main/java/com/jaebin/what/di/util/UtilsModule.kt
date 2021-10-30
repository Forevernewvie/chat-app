package com.jaebin.what.di

import com.jaebin.what.utils.BitmapUtil
import com.jaebin.what.utils.IntentUtils
import org.koin.dsl.module

val UtilsModule = module {
    single { BitmapUtil() }
    single { IntentUtils() }
}