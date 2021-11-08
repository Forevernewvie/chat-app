package com.jaebin.what.bindingadapter

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.jaebin.what.R
import com.jaebin.what.utils.BitmapUtil
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



//kotlin object 객체는 상속이 가능
object BindingImage:KoinComponent {

    private val bitMapUtil: BitmapUtil by inject()

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun loadImage(imgView: ImageView, img: String) {
        val bitMapImg = bitMapUtil.stringToBitMap(img)
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
                .load(bitMapUtil.stringToBitMap(profileImg))
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