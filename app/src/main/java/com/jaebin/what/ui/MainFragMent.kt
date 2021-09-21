package com.jaebin.what.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.FireBaseAPi.Authentication.signUtil
import com.jaebin.what.KeyVariable.RC_SIGN_IN
import com.jaebin.what.R
import com.jaebin.what.bottomView.HomeFragment
import com.jaebin.what.databinding.FragmentMainBinding


class MainFragment: Fragment() {

    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    /*
    override fun onStart() {
        super.onStart()
        signUtil.checkLogin(this)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.findPwd.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_findPassWordFragment)
        }

        binding.signUpBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

        binding.Login.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pwd = binding.pwdText.text.toString()
            signUtil.singUpBasic(email,pwd,requireContext(),this)
        }

        binding.googleSignUpBtn.setOnClickListener {

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context?.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(context, gso)
            val signInIntent = googleSignInClient.signInIntent
            singUpGoogle.launch(signInIntent)
        }

    }


    private val singUpGoogle: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("GooGle", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("GooGle", "Google sign in failed", e)
                }

            }
        }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GooGle", "signInWithCredential:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GooGle", "signInWithCredential:failure", task.exception)
                }
            }
    }
}






