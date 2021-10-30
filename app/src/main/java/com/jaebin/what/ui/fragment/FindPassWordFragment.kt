package com.jaebin.what.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentFindPassWordBinding
import com.jaebin.what.signutil.Login
import org.koin.android.ext.android.inject

class FindPassWordFragment : Fragment() {
    private lateinit var findPwdBinding: FragmentFindPassWordBinding
    private val loginUtil : Login by inject()

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
            loginUtil.findPassword(email)
            it.findNavController().navigate(R.id.action_findPassWordFragment_to_mainFragment)

        }
    }
}