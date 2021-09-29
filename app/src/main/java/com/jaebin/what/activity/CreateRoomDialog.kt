package com.jaebin.what.activity

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaebin.what.Extension.makeRandomString
import com.jaebin.what.ConstantsVal.DIALOG_TITLE
import com.jaebin.what.R
import com.jaebin.what.databinding.DialogBinding
import com.jaebin.what.fireBaseAPi.Authentication
import com.jaebin.what.fireBaseAPi.ChatRoomDataBase
import com.jaebin.what.model.ChatRoomModel

class CreateRoomDialog :AppCompatActivity() {

    private lateinit var dialogBinding: DialogBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        dialogBinding = DataBindingUtil.setContentView(this, R.layout.dialog)
    }

    fun dialogBuilder(){
        var builder = AlertDialog.Builder(applicationContext)
        builder.setTitle(DIALOG_TITLE)
        val randomString = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        val listener = DialogInterface.OnClickListener { _, _ ->
            val chatRoomInfoData = ChatRoomModel(dialogBinding.roomNm.text.toString(),dialogBinding.capacity.text.toString(),
                Authentication.auth.uid, randomString.makeRandomString())
            ChatRoomDataBase.chatRoomRef.child(dialogBinding.roomNm.text.toString()).push().setValue(chatRoomInfoData)
        }

        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()

    }

}