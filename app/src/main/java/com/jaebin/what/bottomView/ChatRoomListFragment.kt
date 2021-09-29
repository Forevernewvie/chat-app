package com.jaebin.what.bottomView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.fireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentRoomlistBinding
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.recyclerView.ChatRoomDataAdapter
import com.jaebin.what.viewModel.ChatRoomListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ChatRoomListFragment : Fragment() {
    private lateinit var roomListBinding : FragmentRoomlistBinding
    private lateinit var ChatDataAdapter: ChatRoomDataAdapter
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        roomListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_roomlist,container,false)
        roomListBinding.lifecycleOwner=this.viewLifecycleOwner
        return roomListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        getChatRoomInfo()

        chatRoomListViewModel.roomList.observe(viewLifecycleOwner, Observer {
            ChatDataAdapter.setData(it)
        })

    }

    private fun getChatRoomInfo(){
        chatRoomListViewModel.clear()
        chatRoomRef.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                for (data in snapshot.children) {
                    var testItem = data.getValue(ChatRoomModel::class.java)!!
                    chatRoomListViewModel.addItem(testItem)
                }
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

    private fun initRecyclerView(){
        ChatDataAdapter = ChatRoomDataAdapter()
        roomListBinding.RoomRecycle.layoutManager = LinearLayoutManager(context)
        roomListBinding.RoomRecycle.adapter = ChatDataAdapter
        roomListBinding.RoomRecycle.setHasFixedSize(true)
    }

    private fun initViewModel(){
        chatRoomListViewModel = ViewModelProvider(requireActivity()).get(ChatRoomListViewModel::class.java)
    }

}

