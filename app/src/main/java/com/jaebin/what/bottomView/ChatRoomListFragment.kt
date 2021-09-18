package com.jaebin.what.bottomView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.databinding.FragmentRoomlistBinding
import com.jaebin.what.model.ChatRoomModel
import com.jaebin.what.recyclerView.ChatRoomDataAdapter
import com.jaebin.what.viewModel.ChatRoomListViewModel

class ChatRoomListFragment : Fragment() {
    private var mBinding: FragmentRoomlistBinding?=null
    private val binding get() = mBinding!!
    private lateinit var ChatDataAdapter: ChatRoomDataAdapter
    private lateinit var chatRoomListViewModel: ChatRoomListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentRoomlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()

        chatRoomListViewModel.roomList.observe(viewLifecycleOwner, Observer {
            ChatDataAdapter.setData(it)
        })


        getChatRoomInfo()

    }


    private fun getChatRoomInfo(){
        chatRoomRef.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                for (data in snapshot.children) {
                    val testItem = data.getValue(ChatRoomModel::class.java)!!
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
        binding.RoomRecycle.layoutManager = LinearLayoutManager(context)
        binding.RoomRecycle.adapter = ChatDataAdapter
        binding.RoomRecycle.setHasFixedSize(true)
    }

    private fun initViewModel(){
        chatRoomListViewModel = ViewModelProvider(requireActivity()).get(ChatRoomListViewModel::class.java)
    }



}

