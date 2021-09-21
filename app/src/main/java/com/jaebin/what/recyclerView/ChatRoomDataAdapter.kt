package com.jaebin.what.recyclerView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.FireBaseAPi.ChatDataBase.chatDataRef
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.KeyVariable.posKey
import com.jaebin.what.KeyVariable.ref
import com.jaebin.what.R
import com.jaebin.what.databinding.ChatcardBinding
import com.jaebin.what.model.ChatRoomModel


class ChatRoomDataAdapter : RecyclerView.Adapter<ChatRoomDataAdapter.ViewHolder>() {
    private lateinit var binding:ChatcardBinding
    private var roomData = mutableListOf<ChatRoomModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ChatcardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = roomData[position]

        holder.itemBinding.usersNum.text = data.num.toString()
        holder.itemBinding.roomName.text = data.roomNm
        holder.itemBinding.deleteBtn.setOnClickListener {
            if (data.uid == auth.uid) {
                deleteData(position)

                chatRoomRef.child(ref).child(data.roomNm!!).key?.let {
                    chatRoomRef.child(it).removeValue()
                }
                chatDataRef.key?.let { chatDataRef.removeValue() }
            }else{
                Toast.makeText(it.context,"방을 개설한 사람이 아니면 삭제 불가능합니다",Toast.LENGTH_SHORT).show()
            }

        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(posKey, position)
            it.findNavController() .navigate(R.id.action_chatRoomListFragment_to_chatRoomFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return roomData.size
    }


    class ViewHolder(val  itemBinding:ChatcardBinding) : RecyclerView.ViewHolder(itemBinding.root)

    fun setData(data : ArrayList<ChatRoomModel>){
        roomData = data
        notifyDataSetChanged()
    }


     fun deleteData(pos :Int){
        if (pos<0){ return }
        else{
            roomData.removeAt(pos)
            notifyDataSetChanged()
        }
    }
}

