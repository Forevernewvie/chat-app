package com.jaebin.what.ui.bottomview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentInfoBinding

class InfoFragment :Fragment() {
    private lateinit var infoBinding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        infoBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false)
        return infoBinding.root
    }

}