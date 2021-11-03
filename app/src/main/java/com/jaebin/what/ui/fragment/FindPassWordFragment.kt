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
import com.jaebin.what.databinding.FragmentFindPassWordBinding
import com.jaebin.what.utils.OnSuccessOrFail
import com.jaebin.what.viewmodel.FindPwdViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindPassWordFragment : Fragment() {
    private lateinit var findPwdBinding: FragmentFindPassWordBinding
    private val findPwdViewModel:FindPwdViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        findPwdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_pass_word,container,false)
        findPwdBinding.lifecycleOwner = this.viewLifecycleOwner
        findPwdBinding.findPwdVM = findPwdViewModel
        return findPwdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findPwdBinding.confirmBtn.setOnClickListener {
            findPwdViewModel.findPassword(object:OnSuccessOrFail{
                override fun onSuccess() {
                    it.findNavController().navigate(R.id.action_findPassWordFragment_to_mainFragment)
                }

                override fun onFail() {
                    Toast.makeText(context,"이메일 재입력", Toast.LENGTH_SHORT).show()
                }

                override fun onEmailOrPassWordERR() {
                }

                override fun onInValidateEmail() {
                    Toast.makeText(context,"양식에 맞지 않는 이메일", Toast.LENGTH_SHORT).show()
                }

                override fun onSendEmail() {
                    Toast.makeText(context,"비밀번호 변경 이메일 전송 완료", Toast.LENGTH_SHORT).show()
                }

            })


        }
    }
}