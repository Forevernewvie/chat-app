package com.jaebin.what.data.msg


import com.jaebin.what.data.msg.remote.MsgRemoteDataSource
import com.jaebin.what.model.Msg
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MsgRepositoryImpl @Inject constructor(
    private val msgRemoteDataSource : MsgRemoteDataSource
) :MsgRepository{
    override fun fetchMsgData(): Flowable<List<Msg>> {
        return msgRemoteDataSource.fetchMsgData().observeOn(Schedulers.io())
    }
}