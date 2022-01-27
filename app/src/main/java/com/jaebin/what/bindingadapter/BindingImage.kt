package com.jaebin.what.bindingadapter

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.jaebin.what.R
import com.jaebin.what.utils.BitmapUtil
import javax.inject.Inject


//kotlin object 객체는 상속이 가능
object BindingImage {

    @Inject
    lateinit var bitmapUtil: BitmapUtil

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun loadImage(imgView: ImageView, img: String) {
        val bitMapImg = bitmapUtil.stringToBitMap(img)
        Glide
            .with(imgView.context)
            .load(bitMapImg)
            .centerCrop()
            .override(180, 210)
            .into(imgView)
    }

    @JvmStatic
    @BindingAdapter("profileImgSrc")
    fun loadProfileImg(imgView: ImageView, profileImg: String?) {
        if (profileImg.isNullOrEmpty()) {
            Toast.makeText(imgView.context, "프로필 이미지가 없어요", Toast.LENGTH_SHORT).show()
        } else {
            Glide
                .with(imgView.context)
                .load(bitmapUtil.stringToBitMap(profileImg))
                .centerCrop()
                .into(imgView)

        }
    }

    
    @JvmStatic
    @BindingAdapter("createProfileImgSrc")
    fun loadCreateProfileImg(imgView: ImageView, createProfileImg: Bitmap) {
        if (createProfileImg == null) {
            Toast.makeText(imgView.context, "선택 취소", Toast.LENGTH_SHORT).show()
        } else {
            Glide
                .with(imgView.context)
                .load(createProfileImg)
                .centerCrop()
                .into(imgView)
        }
    }
}