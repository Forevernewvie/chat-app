package com.jaebin.what.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.jaebin.what.fireBaseAPi.Authentication.signUtil
import com.jaebin.what.ConstantsVal.RC_SIGN_IN
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentMainBinding
import com.jaebin.what.fireBaseAPi.Authentication.googleUtil


class MainFragment: Fragment() {

    private lateinit var mainFragmentBinding:FragmentMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFragmentBinding.findPwd.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_findPassWordFragment)
        }

        mainFragmentBinding.signUpBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

        mainFragmentBinding.Login.setOnClickListener {
            val email = mainFragmentBinding.emailText.text.toString()
            val pwd = mainFragmentBinding.pwdText.text.toString()
            signUtil.singUpBasic(email,pwd,requireContext(),this)
        }

        mainFragmentBinding.googleSignUpBtn.setOnClickListener {
            googleSignInClient = GoogleSignIn.getClient(context,context?.let {  googleUtil.getGso(it) })
            val signInIntent = googleSignInClient.signInIntent
            singUpGoogle.launch(signInIntent)
        }

    }


    private val singUpGoogle: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("GooGle", "firebaseAuthWithGoogle:" + account.id)
                    googleUtil.firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Log.w("GooGle", "Google sign in failed", e)
                }

            }
        }


}






