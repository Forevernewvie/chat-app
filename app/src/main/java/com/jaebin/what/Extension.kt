package com.jaebin.what


import java.text.SimpleDateFormat
import java.util.*

object Extension {

    fun  Long.longtoDateTime(): String {
        val date = Date(this)
        val realTime = SimpleDateFormat("YYYY-MM-dd")
        return realTime.format(date)
    }

    fun List<Char>.makeRandomString(): String{
        val stringLength = 10
        val randomString = (0 until stringLength)
            .map { _ -> kotlin.random.Random.nextInt(0, this.size) }
            .map(this::get)
            .joinToString("");

        return randomString
    }

}