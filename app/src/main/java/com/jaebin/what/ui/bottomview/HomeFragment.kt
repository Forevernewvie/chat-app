package com.jaebin.what.bottomview

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.jaebin.what.ConstantsVal.DIALOG_TITLE
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_IMG_KEY
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_KEY
import com.jaebin.what.Extension.makeRandomString
import com.jaebin.what.R
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.databinding.DialogBinding
import com.jaebin.what.databinding.FragmentHomeBinding
import com.jaebin.what.firebaseapi.Authentication.auth
import com.jaebin.what.firebaseapi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.utils.BitmapUtil
import com.jaebin.what.viewmodel.ChatRoomListViewModel
import com.jaebin.what.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var homeBinding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeBinding.lifecycleOwner=this.viewLifecycleOwner
        homeBinding.homeViewModel = homeViewModel
        return homeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.setHomeView()

        homeBinding.plusRoomBtn.setOnClickListener {
            context?.let { it -> roomInfoDialog(it) }
        }

        homeBinding.btnSetProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment_home_to_createProfile)
        }
    }

    private fun roomInfoDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(DIALOG_TITLE)
        val dialogBinding= DialogBinding.inflate(LayoutInflater.from(context))
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

