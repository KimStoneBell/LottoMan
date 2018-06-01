package com.stonebell.lottoman.info

import com.google.gson.annotations.SerializedName
data class LottoData(
        @SerializedName("drwNo") val gameNum: Int,
        @SerializedName("firstAccumamnt") val firstAccumamnt: Long,
        @SerializedName("firstWinamnt") val firstWinamnt: Long,
        @SerializedName("returnValue") val returnValue: String,
        @SerializedName("totSellamnt") val totSellamnt: Long,
        @SerializedName("drwtNo1") val ball1: Int,
        @SerializedName("drwtNo2") val ball2: Int,
        @SerializedName("drwtNo3") val ball3: Int,
        @SerializedName("drwtNo4") val ball4: Int,
        @SerializedName("drwtNo5") val ball5: Int,
        @SerializedName("drwtNo6") val ball6: Int,
        @SerializedName("bnusNo") val bnusBall: Long,
        @SerializedName("drwNoDate") val drwNoDate: String,
        @SerializedName("firstPrzwnerCo") val firstPrzwnerCo: Int
)