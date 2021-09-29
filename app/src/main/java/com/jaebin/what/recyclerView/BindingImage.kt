package com.jaebin.what.recyclerView

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jaebin.what.utils.Utils.bitMapUtil


object BindingImage {
    @JvmStatic
    @BindingAdapter("imageSrc")
     fun loadImage(imgView: ImageView, img: String){
        val bitMapImg = bitMapUtil.stringToBitMap(img)
        Glide
            .with(imgView.context)
            .load(bitMapImg)
            .centerCrop()
            .override(180,210)
            .into(imgView)
    }


}