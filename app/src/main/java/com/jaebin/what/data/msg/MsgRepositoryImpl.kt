package com.jaebin.what.data.msg

import com.jaebin.what.data.msg.remote.MsgRemoteDataSourceImpl
import com.jaebin.what.data.msg.remote.OnMsgDataListener
import com.jaebin.what.model.Msg
import com.jaebin.what.utils.OnDataListenSuccessOrFail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MsgRepositoryImpl :MsgRepository,KoinComponent{

    private val msgRemoteDataSource: MsgRemoteDataSourceImpl by inject()

    override fun fetchMsgData(callback: OnDataListenSuccessOrFail<Msg>) {
        msgRemoteDataSource.fetchMsgData(object :OnMsgDataListener{
            override fun success(msg: Msg) {
               callback.success(msg)
            }

            override fun fail(errMsg: String) {
               callback.fail(errMsg)
            }
        })
    }

}