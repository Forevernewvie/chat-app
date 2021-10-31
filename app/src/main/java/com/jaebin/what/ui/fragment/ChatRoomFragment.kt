package com.jaebin.what.ui.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaebin.what.ConstantsVal
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.firebaseapi.Authentication.auth
import com.jaebin.what.ConstantsVal.POS_KEY
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_KEY
import com.jaebin.what.R
import com.jaebin.what.data.profile.local.ProfileLocalDataSourceImpl
import com.jaebin.what.databinding.FragmentChatroomBinding
import com.jaebin.what.firebaseapi.ChatDataBase
import com.jaebin.what.model.Msg
import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.ChatContentAdapter.Companion.IMAGE
import com.jaebin.what.recyclerView.ChatContentAdapter.Companion.MSG
import com.jaebin.what.utils.BitmapUtil
import com.jaebin.what.utils.IntentUtils
import com.jaebin.what.viewmodel.ChatRoomListViewModel
import com.jaebin.what.viewmodel.ChatRoomViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChatRoomFragment : Fragment() {
    private lateinit var chatRoomBinding: FragmentChatroomBinding
    private val chatRoomViewModel: ChatRoomViewModel by sharedViewModel()
    private val chatRoomListViewModel: ChatRoomListViewModel by sharedViewModel()
    private val intentUtils : IntentUtils by inject()
    private  val chatContentAdapter:ChatContentAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        chatRoomBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chatroom,container,false)
        chatRoomBinding.lifecycleOwner=this.viewLifecycleOwner
        chatRoomBinding.msg =chatRoomViewModel
        return chatRoomBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pos = arguments?.getInt(POS_KEY)!!
        val roomName =  chatRoomListViewModel.roomInfoData.value?.get(pos)?.roomNm.toString()
        val headCount =  chatRoomListViewModel.roomInfoData.value?.get(pos)?.num.toString()

        initRecyclerView()

        chatRoomViewModel.setTitleChatRoom(roomName, headCount)
        chatRoomViewModel.getChatContentList()

        chatRoomBinding.sendButton.setOnClickListener {
            chatRoomViewModel.setChatContent()
            chatRoomBinding.chatText.setText("")
        }

        chatRoomBinding.imgContent.setOnClickListener {
            uploadImg()
        }

        chatRoomViewModel.chatContentData.observe(viewLifecycleOwner,{
            chatContentAdapter.setData(it)
        })

    }

    private fun uploadImg(){
        img.launch( intentUtils.getSAF())
    }


    private fun initRecyclerView(){
        chatRoomBinding.chatRoomRecycle.layoutManager = LinearLayoutManager(context)
        chatRoomBinding.chatRoomRecycle.adapter = chatContentAdapter
        chatRoomBinding.chatRoomRecycle.scrollToPosition(chatContentAdapter.itemCount-1)
        chatRoomBinding.chatRoomRecycle.setHasFixedSize(true)
    }

    private val img: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val context = requireContext()
            if(it.resultCode == AppCompatActivity.RESULT_OK && it.data !=null) {
                val currentImageUri = it.data?.data
                var bitmap: Bitmap?

                try {
                    currentImageUri?.let {
                        if(Build.VERSION.SDK_INT < 28) {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                context.contentResolver,
                                currentImageUri
                            )

                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver, currentImageUri)
                            bitmap = ImageDecoder.decodeBitmap(source)
                            chatRoomViewModel.setImgContent(bitmap!!)
                        }
                    }

                }catch(e:Exception) {
                    e.printStackTrace()
                }
            } else if(it.resultCode == AppCompatActivity.RESULT_CANCELED){
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }else{
                Log.d("ActivityResult","something wrong")
            }
        }




}