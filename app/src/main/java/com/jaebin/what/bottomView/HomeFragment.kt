package com.jaebin.what.bottomView

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jaebin.what.Extension.makeRandomString
import com.jaebin.what.Extension.stringToBitMap
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.FireBaseAPi.Authentication.signUtil
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.KeyVariable.sharedPreferencesImgKey
import com.jaebin.what.KeyVariable.sharedPreferencesKey
import com.jaebin.what.R
import com.jaebin.what.viewModel.ChatRoomListViewModel
import com.jaebin.what.databinding.DialogBinding
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.databinding.FragmentHomeBinding
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.recyclerView.ChatContentAdapter

class HomeFragment :Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProfile()

        chatRoomListViewModel = ViewModelProvider(requireActivity()).get(ChatRoomListViewModel::class.java)
        binding.plusRoomBtn.setOnClickListener {
            context?.let { it -> roomInfoDialog(it) }
        }


        binding.btnSetProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_home_to_createProfile)
        }

    }


    private fun setProfile(){
        binding.messengerTextView.text = SPF.prefs.getString(sharedPreferencesKey,"")
        val profileImg = SPF.prefs.getString(sharedPreferencesImgKey,"")
        if (profileImg == ""){
            Toast.makeText(context,"프로필 이미지가 없어요",Toast.LENGTH_SHORT).show()
        }else{
            Glide
                .with(this)
                .load(stringToBitMap(profileImg))
                .centerCrop()
                .into( binding.homeProfileImg)
        }

    }


    private fun roomInfoDialog(context:Context) {
        var builder = AlertDialog.Builder(context)
        builder.setTitle("채팅방 설정")
        var dialogBinding= DialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(dialogBinding.root)

        val randomString = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        val listener = DialogInterface.OnClickListener { _, _ ->
            val chatRoomInfoData = ChatRoomModel(dialogBinding.roomNm.text.toString(),dialogBinding.capacity.text.toString(),
                auth.uid, randomString.makeRandomString())
            chatRoomRef.child(dialogBinding.roomNm.text.toString()).push().setValue(chatRoomInfoData)
        }

        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()

    }

}

