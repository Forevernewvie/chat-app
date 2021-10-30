package com.jaebin.what.signutil

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.jaebin.what.firebaseapi.Authentication.auth

class Login(context: Context) {

     private val context = context

     fun singIn(email:String,pwd:String){

        if (email == "" || pwd == ""){
            Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
        } else{
            auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener {
                if (it.isSuccessful && auth.currentUser?.isEmailVerified == true){
                    Toast.makeText(context,"로그인 완료", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

     private fun signUp(email:String, pwd:String){
        if (email == "" || pwd == ""){
            Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(context,"가입 완료",Toast.LENGTH_SHORT).show()
                    verifyEmail()
                }else{
                    Toast.makeText(context,"가입 실패",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun login(email:String,pwd:String){
        if(validateEmail(email)){
            signUp(email,pwd)
        }else{
            Toast.makeText(context,"양식에 맞지 않는 이메일",Toast.LENGTH_SHORT).show()
        }
    }


     private fun verifyEmail(){
        auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(context,"이메일 전송, 인증 완료 시 로그인 가능",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"${it.exception}",Toast.LENGTH_SHORT).show()
                }
            }
    }

     fun resetPassword(email:String){
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"비밀번호 변경 이메일 전송 완료",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"이메일 재입력",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun findPassword(email:String){
        if(validateEmail(email)){
           resetPassword(email)
        }else{
            Toast.makeText(context,"양식에 맞지 않는 이메일",Toast.LENGTH_SHORT).show()
        }
    }




    private fun validateEmail(email:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }








}