package com.jaebin.what.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jaebin.what.FireBaseAPi.Authentication.signUtil
import com.jaebin.what.databinding.FragmentFindPassWordBinding



class FindPassWordFragment : Fragment() {
    private lateinit var binding: FragmentFindPassWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindPassWordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmBtn.setOnClickListener {
            val email = binding.emailText.text.toString()

            if(signUtil.validateEmail(email)){
                signUtil.resetPassword(email,it,requireContext())
            }
            else{
                Toast.makeText(requireContext(),"양식에 맞지 않는 이메일",Toast.LENGTH_SHORT).show()
            }

        }

    }




}