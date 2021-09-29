package com.jaebin.what.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
    }
    override fun onSupportNavigateUp() = findNavController(com.jaebin.what.R.id.nav_controller).navigateUp()

}