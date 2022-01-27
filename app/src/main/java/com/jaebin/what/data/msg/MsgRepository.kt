package com.jaebin.what.data.msg


import com.jaebin.what.model.Msg
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import io.reactivex.rxjava3.core.Flowable

interface MsgRepository {
    fun fetchMsgData() : Flowable<List<Msg>>
}




