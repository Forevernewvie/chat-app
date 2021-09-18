package com.jaebin.what.ui

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
=======
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
>>>>>>> chat
import androidx.appcompat.app.AppCompatActivity
import com.jaebin.what.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            delay(500L)
            startActivity(intent)
            finish()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}