package com.jaebin.what.utils


interface OnDataListenSuccessOrFail<T> {
    fun success(item: T)
    fun fail(errMsg:String)
}