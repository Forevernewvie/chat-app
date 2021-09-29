package com.jaebin.what.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.ConstantsVal.POS_KEY
import com.jaebin.what.ConstantsVal.REF
import com.jaebin.what.R
import com.jaebin.what.databinding.ChatcardBinding
import com.jaebin.what.fireBaseAPi.Authentication
import com.jaebin.what.fireBaseAPi.ChatDataBase
import com.jaebin.what.fireBaseAPi.ChatRoomDataBase
import com.jaebin.what.model.ChatRoomModel


class ChatRoomDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var roomData = mutableListOf<ChatRoomModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val chatRoomItemBinding =ChatcardBinding.inflate(inflater,parent,false)
        return ChatRoomItemViewHolder(chatRoomItemBinding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ChatRoomItemViewHolder).bind(roomData[position],position)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(POS_KEY, position)
            it.findNavController() .navigate(R.id.action_chatRoomListFragment_to_chatRoomFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return roomData.size
    }

    inner class ChatRoomItemViewHolder(private val chatRoomItemBinding: ChatcardBinding) : RecyclerView.ViewHolder(chatRoomItemBinding.root) {


        fun bind(data: ChatRoomModel, pos: Int) {
            chatRoomItemBinding.model = data

            chatRoomItemBinding.deleteBtn.setOnClickListener { it ->
                if (data.uid == Authentication.auth.uid) {
                    deleteData(pos)
                    ChatRoomDataBase.chatRoomRef.child(REF)
                        .child(data.roomNm!!).key?.let {
                        ChatRoomDataBase.chatRoomRef.child(it).removeValue()
                    }
                    ChatDataBase.chatDataRef.key?.let { ChatDataBase.chatDataRef.removeValue() }

                } else {
                    Toast.makeText(it.context, "방을 개설한 사람이 아니면 삭제 불가능합니다", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }





    fun setData(data : ArrayList<ChatRoomModel>){
        roomData = data
        notifyDataSetChanged()
    }


     fun deleteData(pos :Int){
        if (pos<0){ return
        }else{
            roomData.removeAt(pos)
            notifyDataSetChanged()
        }
    }
}

