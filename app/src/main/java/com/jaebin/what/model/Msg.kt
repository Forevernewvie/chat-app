package com.jaebin.what.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jaebin.what.Extension

data class Msg(
    var viewType:Int? =null,
    var name:String? =null,
    var msg:String? =null ,
    val time:String? =null,
    var uid:String? =null,
    var img:String? = null
)




