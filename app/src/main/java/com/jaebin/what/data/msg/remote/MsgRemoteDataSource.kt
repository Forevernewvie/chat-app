package com.jaebin.what.data.msg.remote

import com.jaebin.what.model.Msg

interface MsgRemoteDataSource {
    fun fetchMsgData(callback: OnMsgDataListener)
}


interface OnMsgDataListener{
    fun success(msg: Msg)
    fun fail(errMsg:String)
}


