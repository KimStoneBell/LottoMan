package com.stonebell.lottoman.reactivex.common

import java.util.*

class RxLog{
    companion object {
        fun d(data: String){
            val date = Calendar.getInstance()
            val time = "time : " + date.get(Calendar.HOUR) + ":"+ date.get(Calendar.MINUTE) + ":"+ date.get(Calendar.SECOND) + ":"+ date.get(Calendar.MILLISECOND)
            println("${Thread.currentThread().name} | time | $time | value = $data")
        }
    }
}