package com.jaebin.what.bottomView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentInfoBinding

class InfoFragment :Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

}