package com.jaebin.what.signUtil

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
import com.jaebin.what.ui.MainFragment
import com.jaebin.what.ui.SignUpFragment

class Basic {
    private lateinit var navController: NavController


     fun singUpBasic(email:String,pwd:String,context:Context,view: MainFragment){

        if (email == "" || pwd == ""){
            Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener {
                if (it.isSuccessful){
                    navController=findNavController(view)
                    navController.navigate(R.id.action_mainFragment_to_bottomViewActivity)
                    Toast.makeText(context,"로그인 완료", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(context,"로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

     fun signInBasic(email:String,pwd:String,context:Context,view: SignUpFragment){
        if (email == "" || pwd == ""){
            Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener{

                if (it.isSuccessful){
                    Toast.makeText(context,"가입 완료",Toast.LENGTH_SHORT).show()
                    navController = findNavController(view)
                    navController.navigate(R.id.action_signUpFragment_to_mainFragment)
                }else{
                    Toast.makeText(context,"가입 실패",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun checkLogin(currentUser: FirebaseUser?,view:MainFragment) {
        if ( currentUser !=null ){
            navController = findNavController(view)
            navController.navigate(R.id.action_mainFragment_to_bottomViewActivity)
        }

    }






}