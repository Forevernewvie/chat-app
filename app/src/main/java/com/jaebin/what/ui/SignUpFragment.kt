package com.jaebin.what.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
<<<<<<< HEAD
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding
=======
import com.jaebin.what.FireBaseAPi.Authentication.signUtil
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding
import com.jaebin.what.signUtil.Basic
>>>>>>> chat

class SignUpFragment: Fragment() {
    private var mBinding: FragmentSignupBinding?=null
    private val binding get() = mBinding!!
<<<<<<< HEAD
    private lateinit var navController:NavController
    private lateinit var callBack: OnBackPressedCallback
=======

>>>>>>> chat
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
<<<<<<< HEAD

=======
>>>>>>> chat
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sumbit.setOnClickListener {
<<<<<<< HEAD
            val Email = binding.SignUpEmailText.text.toString()
            val Pwd = binding.signUpPwdText.text.toString()

            if (Email == "" || Pwd == ""){
                Toast.makeText(context,"아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(Email,Pwd).addOnCompleteListener{

                    if (it.isSuccessful){
                        Toast.makeText(context,"가입 완료",Toast.LENGTH_SHORT).show()
                        navController = Navigation.findNavController(view)
                        navController.navigate(R.id.action_signUpFragment_to_mainFragment)
                    }else{
                        Toast.makeText(context,"가입 실패",Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }


=======
            val email = binding.SignUpEmailText.text.toString()
            val pwd = binding.signUpPwdText.text.toString()
            context?.let { it1 -> signUtil.signInBasic(email,pwd, it1,this) }
        }
>>>>>>> chat
    }



    override fun onDestroyView() {
        mBinding =null
        super.onDestroyView()
    }
}

