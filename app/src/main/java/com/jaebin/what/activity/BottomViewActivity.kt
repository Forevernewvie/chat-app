package com.jaebin.what.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.ActivityBottomViewBinding

class BottomViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBottomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host)
        binding.bottomView.setupWithNavController(navController)
    }
}