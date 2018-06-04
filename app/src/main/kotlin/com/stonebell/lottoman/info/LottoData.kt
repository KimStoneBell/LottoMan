package com.stonebell.lottoman.info

import com.google.gson.annotations.SerializedName
data class LottoData(
        @SerializedName("drwNo") val gameNum: Int = 0,
        @SerializedName("firstAccumamnt") val firstAccumamnt: Long = 0,
        @SerializedName("firstWinamnt") val firstWinamnt: Long = 0,
        @SerializedName("returnValue") val returnValue: String = "EMPTY",
        @SerializedName("totSellamnt") val totSellamnt: Long = 0,
        @SerializedName("drwtNo1") val ball1: Int = 0,
        @SerializedName("drwtNo2") val ball2: Int = 0,
        @SerializedName("drwtNo3") val ball3: Int = 0,
        @SerializedName("drwtNo4") val ball4: Int = 0,
        @SerializedName("drwtNo5") val ball5: Int = 0,
        @SerializedName("drwtNo6") val ball6: Int = 0,
        @SerializedName("bnusNo") val bnusBall: Long = 0,
        @SerializedName("drwNoDate") val drwNoDate: String = "EMPTY",
        @SerializedName("firstPrzwnerCo") val firstPrzwnerCo: Int = 0
)