package com.jaebin.what.data.msg


import com.jaebin.what.model.Msg
import com.jaebin.what.utils.OnDataListenSuccessOrFail

interface MsgRepository {
    fun fetchMsgData(callback: OnDataListenSuccessOrFail<Msg>)
}




