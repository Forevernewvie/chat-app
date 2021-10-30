package com.jaebin.what.recyclerView

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jaebin.what.firebaseapi.Authentication
import com.jaebin.what.databinding.ChatImgItemBinding
import com.jaebin.what.model.Msg

class ImageItemViewHolder(val imgItem: ChatImgItemBinding) : RecyclerView.ViewHolder(imgItem.root){

    fun bind(data: Msg) {
        imgItem.imgItem = data

        if (data.uid == Authentication.auth.uid) {
            imgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            imgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            imgItem.chatImgContent.scaleType = ImageView.ScaleType.FIT_END

        }
        else{
            imgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            imgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            imgItem.chatImgContent.scaleType = ImageView.ScaleType.FIT_START
        }
    }



}