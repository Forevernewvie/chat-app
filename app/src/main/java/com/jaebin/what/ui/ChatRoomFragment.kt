package com.jaebin.what.ui

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.fireBaseAPi.Authentication.auth
import com.jaebin.what.fireBaseAPi.ChatDataBase.chatDataRef
import com.jaebin.what.fireBaseAPi.ChatDataBase.database
import com.jaebin.what.ConstantsVal.POS_KEY
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_KEY
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentChatroomBinding
import com.jaebin.what.model.Msg
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.IMAGE
import com.jaebin.what.recyclerView.MSG
import com.jaebin.what.utils.Utils.bitMapUtil
import com.jaebin.what.utils.Utils.intentUtils
import com.jaebin.what.viewModel.ChatRoomListViewModel
import com.jaebin.what.viewModel.ChatRoomViewModel


class ChatRoomFragment : Fragment() {
    private lateinit var roomName:String
    private lateinit var chatRoomBinding: FragmentChatroomBinding
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel
    private lateinit var chatContentAdapter:ChatContentAdapter
    private lateinit var chatRoomViewModel: ChatRoomViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatRoomBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chatroom,container,false)
        chatRoomBinding.lifecycleOwner=this.viewLifecycleOwner
        return chatRoomBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()
        setTitleChatRoom()

        chatRoomBinding.sendButton.setOnClickListener {
            setChatContent()
        }

        chatRoomBinding.imgContent.setOnClickListener {
            uploadImg()
        }

        chatRoomViewModel._msgList.observe(viewLifecycleOwner,{
            chatContentAdapter.setData(it)
        })

       getChatContent()


    }

    private fun uploadImg(){
        img.launch( intentUtils.getSAF())
    }

    private fun setChatContent(){
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val nickName = SPF.prefs.getString(SHAREDPREFERENCES_KEY,"")
        val uID = auth.uid.toString()
        val chatContent = chatRoomBinding.chatText.text.toString()
        val msgData = Msg(MSG,nickName,chatContent,timeStamp,uID)
        chatDataRef.push().setValue(msgData)
        chatRoomBinding.chatText.setText("")
    }


    private fun getChatContent(){
        chatRoomViewModel.clear()
        chatDataRef.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.getValue(Msg::class.java)?.let { chatRoomViewModel.addItem(it) }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    private fun setTitleChatRoom(){
        val roomListPos= arguments?.getInt(POS_KEY)!!
        roomName = chatRoomListViewModel.roomList.value?.get(roomListPos)?.roomNm.toString()
        val headCount = chatRoomListViewModel.roomList.value?.get(roomListPos)?.num.toString()
        chatDataRef = database.getReference(roomName)
        chatRoomBinding.topAppBar.title = String.format("$roomName ($headCount)")
    }

    private fun initRecyclerView(){
        chatContentAdapter = ChatContentAdapter()
        chatRoomBinding.chatRoomRecycle.layoutManager = LinearLayoutManager(context)
        chatRoomBinding.chatRoomRecycle.adapter = chatContentAdapter
        chatRoomBinding.chatRoomRecycle.scrollToPosition(chatContentAdapter.itemCount-1)
        chatRoomBinding.chatRoomRecycle.setHasFixedSize(true)
    }

    private fun initViewModel(){
        chatRoomViewModel = ViewModelProvider(requireActivity()).get(ChatRoomViewModel::class.java)
        chatRoomListViewModel = ViewModelProvider(requireActivity()).get(ChatRoomListViewModel::class.java)
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
                            val timeStamp = System.currentTimeMillis().longtoDateTime()
                            val nickName = SPF.prefs.getString(SHAREDPREFERENCES_KEY,"")
                            val uID = auth.uid.toString()
                            val source = ImageDecoder.createSource(context.contentResolver, currentImageUri)
                            bitmap = ImageDecoder.decodeBitmap(source)
                            chatDataRef.push().setValue(Msg(viewType = IMAGE,name=nickName,time=timeStamp,uid = uID,
                                img = bitMapUtil.bitMapToString(bitmap!!)))
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