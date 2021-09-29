package com.jaebin.what.bottomView

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.jaebin.what.Extension.makeRandomString
import com.jaebin.what.utils.Utils.bitMapUtil
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_IMG_KEY
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_KEY
import com.jaebin.what.fireBaseAPi.Authentication.auth
import com.jaebin.what.fireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.R
import com.jaebin.what.activity.CreateRoomDialog
import com.jaebin.what.databinding.DialogBinding
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.databinding.FragmentHomeBinding
import com.jaebin.what.model.ChatRoomModel

class HomeFragment :Fragment() {

    private lateinit var homeBinding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProfile()

        homeBinding.plusRoomBtn.setOnClickListener {
            CreateRoomDialog().dialogBuilder()
        }

        homeBinding.btnSetProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_home_to_createProfile)
        }

    }


    private fun setProfile(){
        homeBinding.messengerTextView.text = SPF.prefs.getString(SHAREDPREFERENCES_KEY,"")
        val profileImg = SPF.prefs.getString(SHAREDPREFERENCES_IMG_KEY,"")
        if (profileImg == ""){
            Toast.makeText(context,"프로필 이미지가 없어요",Toast.LENGTH_SHORT).show()
        }else{
            Glide
                .with(this)
                .load(bitMapUtil.stringToBitMap(profileImg))
                .centerCrop()
                .into( homeBinding.homeProfileImg)
        }

    }


}

