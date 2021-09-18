package com.jaebin.what.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
=======
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
>>>>>>> chat
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentMainBinding


class MainFragment: Fragment() {

<<<<<<< HEAD
    private var mBinding : FragmentMainBinding?=null
    private val binding get() = mBinding!!
=======
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var googleSignInClient: GoogleSignInClient
>>>>>>> chat
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
<<<<<<< HEAD
        mBinding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

=======
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        signUtil.checkLogin(auth.currentUser,this)
    }

>>>>>>> chat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signUpBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

<<<<<<< HEAD

        binding.Login.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pwd = binding.pwdText.text.toString()
            if (email == "" || pwd == ""){
                Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener {
                    if (it.isSuccessful){
                        navController=findNavController()
                        navController.navigate(R.id.action_mainFragment_to_bottomViewActivity)
                        Toast.makeText(context,"로그인 완료", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(context,"로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}
=======
        binding.Login.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pwd = binding.pwdText.text.toString()
            context?.let { it1 -> signUtil.singUpBasic(email, pwd, it1, this) }
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






>>>>>>> chat
