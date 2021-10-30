package com.jaebin.what.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.databinding.ChatItemBinding
import com.jaebin.what.model.Msg

class MsgItemViewHolder(val  msgItem: ChatItemBinding) : RecyclerView.ViewHolder(msgItem.root) {


    fun bind(data: Msg){

        msgItem.msg = data

        if(data.uid == Authentication.auth.uid){
            msgItem.chatContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            msgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            msgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
        }
        else{
            msgItem.chatContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            msgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            msgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        }
    }

}