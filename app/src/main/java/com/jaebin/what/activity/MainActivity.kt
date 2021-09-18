package com.jaebin.what.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
import com.jaebin.what.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var mBinding:ActivityMainBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onSupportNavigateUp() = findNavController(com.jaebin.what.R.id.nav_controller).navigateUp()

    override fun onDestroy() {
        mBinding=null
        auth.signOut()
        super.onDestroy()

    }

}