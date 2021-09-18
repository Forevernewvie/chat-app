package com.jaebin.what

<<<<<<< HEAD
=======
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
>>>>>>> chat
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

<<<<<<< HEAD
=======
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

     fun getSAF(): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.data=MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        return intent
    }




>>>>>>> chat
}