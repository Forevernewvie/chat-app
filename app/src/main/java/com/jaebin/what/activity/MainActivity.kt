package com.jaebin.what.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
<<<<<<< HEAD
=======
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
>>>>>>> chat
import com.jaebin.what.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var mBinding:ActivityMainBinding?=null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD

=======
>>>>>>> chat
    }
    override fun onSupportNavigateUp() = findNavController(com.jaebin.what.R.id.nav_controller).navigateUp()

    override fun onDestroy() {
        mBinding=null
<<<<<<< HEAD
        super.onDestroy()
=======
        auth.signOut()
        super.onDestroy()

>>>>>>> chat
    }

}