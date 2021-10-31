package com.jaebin.what.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentSignupBinding
import com.jaebin.what.signutil.Login
import com.jaebin.what.utils.onSuccessOrFail
import com.jaebin.what.viewmodel.SingUpViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment: Fragment() {
    private lateinit var signUpBinding: FragmentSignupBinding
    private val singUpViewModel: SingUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
        signUpBinding.lifecycleOwner = this.viewLifecycleOwner
        signUpBinding.singUpVM = singUpViewModel
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBinding.sumbit.setOnClickListener {
            singUpViewModel.signUp(object :onSuccessOrFail{
                override fun onSuccess() {
                    Toast.makeText(context,"회원가입 완료",Toast.LENGTH_SHORT).show()
                    it.findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                }
                override fun onFail() {
                    Toast.makeText(context,"회원가입 실패",Toast.LENGTH_SHORT).show()
                }

                override fun onEmailOrPassWordERR() {
                    Toast.makeText(context,"이메일과 패스워드가 다릅니다.",Toast.LENGTH_SHORT).show()
                }

                override fun onInValidateEmail() {
                    Toast.makeText(context,"유효하지 않는 이메일",Toast.LENGTH_SHORT).show()
                }

                override fun onSendEmail() {
                    Toast.makeText(context,"인증확인 이메일 발송",Toast.LENGTH_SHORT).show()
                }

            })

        }
    }

}

