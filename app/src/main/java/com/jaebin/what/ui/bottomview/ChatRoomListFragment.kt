package com.jaebin.what.ui.bottomview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaebin.what.R
import com.jaebin.what.databinding.FragmentRoomlistBinding
import com.jaebin.what.recyclerView.ChatRoomListDataAdapter
import com.jaebin.what.viewmodel.ChatRoomListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatRoomListFragment : Fragment() {

    private lateinit var roomListBinding : FragmentRoomlistBinding
    private val chatRoomListViewModel: ChatRoomListViewModel by sharedViewModel()
    private val chatRoomListAdapter : ChatRoomListDataAdapter by inject()

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

        chatRoomListViewModel.getChatRoomList()
        chatRoomListViewModel.roomInfoData.observe(viewLifecycleOwner, Observer {
            chatRoomListAdapter.setData(it)
        })

    }

    private fun initRecyclerView(){
        roomListBinding.RoomRecycle.layoutManager = LinearLayoutManager(context)
        roomListBinding.RoomRecycle.adapter = chatRoomListAdapter
        roomListBinding.RoomRecycle.setHasFixedSize(true)
    }
}





