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
import com.jaebin.what.FireBaseAPi.Authentication.signUtil
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding
import com.jaebin.what.signUtil.Basic

class SignUpFragment: Fragment() {
    private var mBinding: FragmentSignupBinding?=null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sumbit.setOnClickListener {
            val email = binding.SignUpEmailText.text.toString()
            val pwd = binding.signUpPwdText.text.toString()
            context?.let { it1 -> signUtil.signInBasic(email,pwd, it1,this) }
        }
    }



    override fun onDestroyView() {
        mBinding =null
        super.onDestroyView()
    }
}

