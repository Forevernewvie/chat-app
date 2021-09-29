package com.jaebin.what.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class BitmapUtil {
    fun bitMapToString(bitmap: Bitmap): String {

        val byte = ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, this)
        }.toByteArray()

        return Base64.encodeToString(byte, Base64.DEFAULT)
    }

    fun stringToBitMap(encodeString: String?): Bitmap {
        val encodeByte = Base64.decode(encodeString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    }



}