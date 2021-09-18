package com.jaebin.what.recyclerView

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaebin.what.Extension
import com.jaebin.what.FireBaseAPi.Authentication
import com.jaebin.what.databinding.ChatImgItemBinding
import com.jaebin.what.model.Msg

class ImageItemViewHolder(val imgItem: ChatImgItemBinding) : RecyclerView.ViewHolder(imgItem.root){

    fun bind(data: Msg) {

        imgItem.timeStamp.text = data.time
        imgItem.nickName.text = data.name

        if (data.uid == Authentication.auth.uid) {
            imgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            imgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            setImageWithGlide(data)
            imgItem.chatImgContent.scaleType = ImageView.ScaleType.FIT_END

        }
        else{
            imgItem.nickName.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            imgItem.timeStamp.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            setImageWithGlide(data)
            imgItem.chatImgContent.scaleType = ImageView.ScaleType.FIT_START
        }
    }

    private fun setImageWithGlide(data:Msg){
        Glide
            .with(imgItem.root.context)
            .load(Extension.stringToBitMap(data.img))
            .centerCrop()
            .override(180,210)
            .into(imgItem.chatImgContent)
    }

}