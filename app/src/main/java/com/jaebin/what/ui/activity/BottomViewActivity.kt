package com.jaebin.what.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.ActivityBottomViewBinding

class BottomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomBinding :ActivityBottomViewBinding= DataBindingUtil.setContentView(this,R.layout.activity_bottom_view)
        val navController = findNavController(R.id.nav_host)
        bottomBinding.bottomView.setupWithNavController(navController)
    }

}