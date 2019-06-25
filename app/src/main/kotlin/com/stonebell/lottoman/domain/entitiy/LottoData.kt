package com.stonebell.lottoman.domain.entitiy

data class LottoData(
        val gameRound: Int = 0,
        val date: String = "EMPTY",
        val accumamnt: Long = 0,
        val winamnt: Long = 0,
        val przwnerCo: Int = 0,
        val totSellamnt: Long = 0,
        val balls: List<Int> = ArrayList<Int>(),
        val bnusBall: Long = 0
)