package com.jaebin.what.recyclerView

<<<<<<< HEAD
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.FireBaseAPi.Authentication.auth
import com.jaebin.what.databinding.ChatItemBinding
import com.jaebin.what.model.Msg

class ChatContentAdapter() : RecyclerView.Adapter<ChatContentAdapter.ViewHolder>() {


    var chatData = mutableListOf<Msg>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = chatData[position]

        holder.itembinding.chatContent.text = data.msg
        holder.itembinding.nickName.text = data.name
        holder.itembinding.timeStamp.text = data.time

        if(data.uid == auth.uid){
            holder.itembinding.chatContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            holder.itembinding.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            holder.itembinding.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
        }
        else{
            holder.itembinding.chatContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            holder.itembinding.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            holder.itembinding.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        }


    }

=======
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


>>>>>>> chat
    override fun getItemCount(): Int {
        return chatData.size
    }


<<<<<<< HEAD
    class ViewHolder(val  itembinding:ChatItemBinding) : RecyclerView.ViewHolder(itembinding.root)
=======
>>>>>>> chat

    fun setData(data : ArrayList<Msg>){
        chatData = data
        notifyDataSetChanged()
    }

<<<<<<< HEAD
    fun clear(){
        chatData.clear()
        notifyDataSetChanged()
    }
=======
>>>>>>> chat

}

