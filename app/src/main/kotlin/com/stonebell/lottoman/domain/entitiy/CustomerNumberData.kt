package com.stonebell.lottoman.domain.entitiy

/**
 * Created by Johnkim on 2018. 6. 7..
 */
data class CustomerNumberData (
        val type: Int,
        val gameNum: Int,
        val numbers: List<Int>
)