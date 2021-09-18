package com.jaebin.what.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.Extension.stringToBitMap
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.databinding.ChatImgItemBinding
import com.jaebin.what.databinding.ChatItemBinding
import com.jaebin.what.model.Msg
import java.lang.RuntimeException

class ChatContentAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var chatData = mutableListOf<Msg>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val msgView = ChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val imgView = ChatImgItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return when(viewType){

            MSG->MsgItemViewHolder(msgView)
            IMAGE->ImageItemViewHolder(imgView)
            else-> throw RuntimeException()
        }


    }

    override fun getItemViewType(position: Int): Int {
        return chatData[position].viewType!!

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = chatData[position]

        when(data.viewType ){
            MSG->{
                (holder as MsgItemViewHolder).bind(data)
            }

            IMAGE->{
                (holder as ImageItemViewHolder).bind(data)
            }

        }
    }


    override fun getItemCount(): Int {
        return chatData.size
    }



    fun setData(data : ArrayList<Msg>){
        chatData = data
        notifyDataSetChanged()
    }


}

