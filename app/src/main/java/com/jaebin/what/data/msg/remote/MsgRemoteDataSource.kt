package com.jaebin.what.data.msg.remote

import com.jaebin.what.model.Msg
import io.reactivex.rxjava3.core.Flowable

interface MsgRemoteDataSource {
    fun fetchMsgData() : Flowable<List<Msg>>
}



