package com.jaebin.what

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.jaebin.what.preferenceUtil.SPF
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

object Extension {

    fun  Long.longtoDateTime(): String {
        val date = Date(this)
        val realTime = SimpleDateFormat("YYYY-MM-dd")
        return realTime.format(date)
    }

    fun List<Char>.makeRandomString(): String{
        val stringLength = 10
        val randomString = (0 until stringLength)
            .map { _ -> kotlin.random.Random.nextInt(0, this.size) }
            .map(this::get)
            .joinToString("");

        return randomString
    }

}