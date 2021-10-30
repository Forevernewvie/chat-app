package com.jaebin.what.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding
import com.jaebin.what.signutil.Login
import org.koin.android.ext.android.inject

class SignUpFragment: Fragment() {
    private lateinit var signUpBinding: FragmentSignupBinding
    private val loginUtil : Login by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBinding.sumbit.setOnClickListener {
            val email = signUpBinding.SignUpEmailText.text.toString()
            val pwd = signUpBinding.signUpPwdText.text.toString()
            loginUtil.login(email,pwd)
            it.findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }
    }

}

