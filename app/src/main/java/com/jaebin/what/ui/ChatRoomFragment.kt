package com.jaebin.what.ui

import android.content.Intent
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
=======
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
>>>>>>> chat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
<<<<<<< HEAD
import com.google.firebase.database.DatabaseReference
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.FireBaseAPi.ChatDataBase
import com.jaebin.what.FireBaseAPi.ChatDataBase.chatDataRef
import com.jaebin.what.FireBaseAPi.ChatDataBase.database
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.KeyVariable
import com.jaebin.what.KeyVariable.posKey
import com.jaebin.what.activity.MainActivity
import com.jaebin.what.databinding.FragmentChatroomBinding
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.model.Msg
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.recyclerView.ChatContentAdapter
=======
import com.jaebin.what.Extension.bitMapToString
import com.jaebin.what.Extension.getSAF
import com.jaebin.what.Extension.longtoDateTime
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.FireBaseAPi.ChatDataBase.chatDataRef
import com.jaebin.what.FireBaseAPi.ChatDataBase.database
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.KeyVariable
import com.jaebin.what.KeyVariable.posKey
import com.jaebin.what.databinding.FragmentChatroomBinding
import com.jaebin.what.model.Msg
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.recyclerView.ChatContentAdapter
import com.jaebin.what.recyclerView.ChatRoomDataAdapter
import com.jaebin.what.recyclerView.IMAGE
import com.jaebin.what.recyclerView.MSG
>>>>>>> chat
import com.jaebin.what.viewModel.ChatRoomListViewModel
import com.jaebin.what.viewModel.ChatRoomViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
<<<<<<< HEAD
import kotlinx.coroutines.delay
=======
>>>>>>> chat
import kotlinx.coroutines.launch
import java.util.*

class ChatRoomFragment : Fragment() {
    private lateinit var roomName:String
<<<<<<< HEAD
    private lateinit var headCount:String
=======
>>>>>>> chat
    private lateinit var binding: FragmentChatroomBinding
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel
    private lateinit var chatContentAdapter:ChatContentAdapter
    private lateinit var chatRoomViewModel: ChatRoomViewModel
<<<<<<< HEAD
=======


>>>>>>> chat
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatroomBinding.inflate(inflater,container,false)
        return binding.root
    }

<<<<<<< HEAD
=======

>>>>>>> chat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()
        setTitleChatRoom()

        binding.sendButton.setOnClickListener {
            setChatContent()
<<<<<<< HEAD

=======
        }

        binding.imgContent.setOnClickListener {
            uploadImg()
>>>>>>> chat
        }

        chatRoomViewModel._msgList.observe(viewLifecycleOwner,{
            chatContentAdapter.setData(it)
        })

        CoroutineScope(Dispatchers.IO).launch {  getChatContent() }

<<<<<<< HEAD
    }




    private fun setChatContent(){
        val nickName = SPF.prefs.getString(KeyVariable.sharedPreferencesKey,"")
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val uid = auth.uid
        val chatContent = binding.chatText.text.toString()
        val msgData = Msg(nickName,chatContent,timeStamp,uid)
=======

    }

    private fun uploadImg(){
        img.launch( getSAF())
    }

    private fun setChatContent(){
        val timeStamp = System.currentTimeMillis().longtoDateTime()
        val nickName = SPF.prefs.getString(KeyVariable.sharedPreferencesKey,"")
        val uID = auth.uid.toString()
        val chatContent = binding.chatText.text.toString()
        val msgData = Msg(MSG,nickName,chatContent,timeStamp,uID)
>>>>>>> chat
        chatDataRef.push().setValue(msgData)
        binding.chatText.setText("")
    }


<<<<<<< HEAD

    private fun getChatContent(){
=======
    private fun getChatContent(){

>>>>>>> chat
        chatDataRef.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.getValue(Msg::class.java)?.let { chatRoomViewModel.addItem(it) }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
<<<<<<< HEAD

=======
>>>>>>> chat
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    private fun setTitleChatRoom(){
        val roomListPos= arguments?.getInt(posKey)!!
<<<<<<< HEAD
        roomName = chatRoomListViewModel._roomList.value?.get(roomListPos)?.roomNm.toString()
        headCount = chatRoomListViewModel._roomList.value?.get(roomListPos)?.num.toString()
        chatDataRef = database.getReference(roomName)
=======
        roomName = chatRoomListViewModel.roomList.value?.get(roomListPos)?.roomNm.toString()
        val headCount = chatRoomListViewModel.roomList.value?.get(roomListPos)?.num.toString()
        chatDataRef = database.getReference(roomName+"chatContent")
>>>>>>> chat
        binding.topAppBar.title = String.format("$roomName ($headCount)")
    }

    private fun initRecyclerView(){
        chatContentAdapter = ChatContentAdapter()
        binding.chatRoomRecycle.layoutManager = LinearLayoutManager(context)
        binding.chatRoomRecycle.adapter = chatContentAdapter
        binding.chatRoomRecycle.setHasFixedSize(true)
    }

    private fun initViewModel(){
        chatRoomViewModel = ViewModelProvider(requireActivity()).get(ChatRoomViewModel::class.java)
        chatRoomListViewModel = ViewModelProvider(requireActivity()).get(ChatRoomListViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
<<<<<<< HEAD
        chatContentAdapter.clear()
    }

=======
        chatRoomViewModel.clear()
    }
    override fun onStop() {
        super.onStop()
        chatRoomListViewModel.clear()
    }

    private val img: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val context = requireContext()
            if(it.resultCode == AppCompatActivity.RESULT_OK && it.data !=null) {
                var currentImageUri = it.data?.data
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
                            val nickName = SPF.prefs.getString(KeyVariable.sharedPreferencesKey,"")
                            val uID = auth.uid.toString()
                            val source = ImageDecoder.createSource(context.contentResolver, currentImageUri)
                            bitmap = ImageDecoder.decodeBitmap(source)
                            chatDataRef.push().setValue(Msg(viewType = IMAGE,name=nickName,time=timeStamp,uid = uID,img = bitMapToString(bitmap!!)))
                        }
                    }


                }catch(e:Exception) {
                    e.printStackTrace()
                }
            } else if(it.resultCode == AppCompatActivity.RESULT_CANCELED){
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }else{
                Log.d("ActivityResult","something wrong")
            }
        }



>>>>>>> chat

}