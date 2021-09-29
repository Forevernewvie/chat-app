package com.jaebin.what.utils
import android.content.Intent
import android.provider.MediaStore


class IntentUtils {
    fun getSAF(): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.data= MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        return intent
    }
}