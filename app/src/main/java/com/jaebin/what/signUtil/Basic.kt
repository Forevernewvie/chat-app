package com.jaebin.what.signUtil

import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
import com.jaebin.what.ui.MainFragment
import com.jaebin.what.ui.SignUpFragment

class Basic {


     fun singUpBasic(email:String,pwd:String,context:Context,view: MainFragment){

        if (email == "" || pwd == ""){
            Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
        } else{
            auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener {
                if (it.isSuccessful && auth.currentUser?.isEmailVerified == true){
                    view.findNavController().navigate(R.id.action_mainFragment_to_bottomViewActivity)
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
                    verifyEmail(context,view)
                }else{
                    Toast.makeText(context,"가입 실패",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

     private fun verifyEmail(context: Context, view:SignUpFragment){
        auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(context,"이메일 전송, 인증 완료 시 로그인 가능",Toast.LENGTH_SHORT).show()
                    view.findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                }else{
                    Toast.makeText(context,"${it.exception}",Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun checkLogin(view:MainFragment) {
        if ( auth.currentUser !=null ){
            view.findNavController().navigate(R.id.action_mainFragment_to_bottomViewActivity)
        }

    }

     fun resetPassword(email:String,view: View,context: Context){
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"비밀번호 변경 이메일 전송 완료",Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_findPassWordFragment_to_mainFragment)
            }
            else{
                Toast.makeText(context,"이메일 재입력",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun validateEmail(email:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }






}