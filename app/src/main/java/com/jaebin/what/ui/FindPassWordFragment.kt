package com.jaebin.what.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentChatroomBinding
import com.jaebin.what.databinding.FragmentCreateProfileBinding
import com.jaebin.what.databinding.FragmentFindPassWordBinding

class FindPassWord : Fragment() {
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
            val email = binding.emailText.toString()
            resetPassword(email,it)

        }

    }
    private fun resetPassword(email:String,view:View){
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"비밀번호 변경 이메일 전송 완료",Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_findPassWord_to_mainFragment)
            }
            else{
                Toast.makeText(context,"이메일 재입력",Toast.LENGTH_SHORT).show()
            }
        }

    }

}