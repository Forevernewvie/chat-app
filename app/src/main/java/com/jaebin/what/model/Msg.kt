package com.jaebin.what.model

data class Msg(
    var viewType:Int? =null,
    var name:String? =null,
    var msg:String? =null ,
    val time:String? =null,
    var uid:String? =null,
    var img:String? = null
)
