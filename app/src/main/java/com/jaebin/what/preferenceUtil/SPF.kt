package com.jaebin.what.preferenceUtil

import android.app.Application

class SPF: Application()
{
    companion object { lateinit var prefs: PreferenceUtil }
     override fun onCreate()

    {   prefs = PreferenceUtil(applicationContext)
        super.onCreate()
     }


}

