package com.jaebin.what.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jaebin.what.fireBaseAPi.Authentication.signUtil
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding

class SignUpFragment: Fragment() {
    private lateinit var signUpBinding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = DataBindingUtil.inflate(inflater,R.id.signUpFragment,container,false)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBinding.sumbit.setOnClickListener {
            val email = signUpBinding.SignUpEmailText.text.toString()
            val pwd = signUpBinding.signUpPwdText.text.toString()

            if(signUtil.validateEmail(email)){
                signUtil.signInBasic(email,pwd,requireContext(),this)
            }else{
                Toast.makeText(requireContext(),"양식에 맞지 않는 이메일",Toast.LENGTH_SHORT).show()
            }

        }
    }

}

