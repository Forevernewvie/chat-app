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
import com.jaebin.what.databinding.FragmentFindPassWordBinding

class FindPassWordFragment : Fragment() {
    private lateinit var findPwdBinding: FragmentFindPassWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        findPwdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_pass_word,container,false)
        return findPwdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findPwdBinding.confirmBtn.setOnClickListener {
            val email = findPwdBinding.emailText.text.toString()

            if(signUtil.validateEmail(email)){
                signUtil.resetPassword(email,it,requireContext())
            }else{
                Toast.makeText(requireContext(),"양식에 맞지 않는 이메일",Toast.LENGTH_SHORT).show()
            }
        }
    }
}