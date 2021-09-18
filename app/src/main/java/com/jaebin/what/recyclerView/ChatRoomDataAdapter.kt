package com.jaebin.what.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
<<<<<<< HEAD
=======
import android.view.View
>>>>>>> chat
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.FireBaseAPi.Authentication.auth
<<<<<<< HEAD
=======
import com.jaebin.what.FireBaseAPi.ChatDataBase.chatDataRef
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase
>>>>>>> chat
import com.jaebin.what.FireBaseAPi.ChatRoomDataBase.chatRoomRef
import com.jaebin.what.KeyVariable.posKey
import com.jaebin.what.R
import com.jaebin.what.databinding.ChatcardBinding
import com.jaebin.what.model.ChatRoomModel


<<<<<<< HEAD
class ChatRoomDataAdapter() : RecyclerView.Adapter<ChatRoomDataAdapter.ViewHolder>() {
    private lateinit var binding:ChatcardBinding
    var roomData = mutableListOf<ChatRoomModel>()
=======
class ChatRoomDataAdapter : RecyclerView.Adapter<ChatRoomDataAdapter.ViewHolder>() {
    private lateinit var binding:ChatcardBinding
    private var roomData = mutableListOf<ChatRoomModel>()

>>>>>>> chat

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ChatcardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = roomData[position]

<<<<<<< HEAD
        holder.itembinding.usersNum.text = data.num.toString()
        holder.itembinding.roomName.text = data.roomNm
        holder.itembinding.deleteBtn.setOnClickListener {
            if(data.uid == auth.uid) { deleteData(position) }
        }


=======
        holder.itemBinding.usersNum.text = data.num.toString()
        holder.itemBinding.roomName.text = data.roomNm
        holder.itemBinding.deleteBtn.setOnClickListener {
            if(data.uid == auth.uid) {
                deleteData(position)
                chatRoomRef.key?.get(position) .let {chatRoomRef.key?.let{ chatRoomRef.removeValue()} }
                chatDataRef.key?.get(position).let { chatDataRef.key?.let { chatDataRef.removeValue() }}
            }
        }

>>>>>>> chat
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(posKey,position)
            it.findNavController().navigate(R.id.action_chatRoomListFragment_to_chatRoomFragment,bundle)
        }


<<<<<<< HEAD

=======
>>>>>>> chat
    }

    override fun getItemCount(): Int {
        return roomData.size
    }


<<<<<<< HEAD
    class ViewHolder(val  itembinding:ChatcardBinding) : RecyclerView.ViewHolder(itembinding.root)
=======
    class ViewHolder(val  itemBinding:ChatcardBinding) : RecyclerView.ViewHolder(itemBinding.root)
>>>>>>> chat

    fun setData(data : ArrayList<ChatRoomModel>){
        roomData = data
        notifyDataSetChanged()
    }

<<<<<<< HEAD
    fun clear(){
        roomData.clear()
        notifyDataSetChanged()
    }

    fun deleteData(pos :Int){
=======

     fun deleteData(pos :Int){
>>>>>>> chat
        if (pos<0){ return }
        else{
            roomData.removeAt(pos)
            notifyDataSetChanged()
        }
    }
<<<<<<< HEAD


=======
>>>>>>> chat
}

