package com.stonebell.lottoman.data.datasource.web

import com.google.gson.annotations.SerializedName
import com.stonebell.lottoman.domain.entitiy.LottoData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoInfoApi{
    @GET("common.do")
    fun getLottoInfoRx(@Query("drwNo") gameNumber: String, @Query("method") method: String = "getLottoNumber"): Observable<LottoWebData>

        companion object {
            fun create(): LottoInfoApi {

                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create())
                        .addConverterFactory(
                                GsonConverterFactory.create())

                        .baseUrl("http://www.nlotto.co.kr/")
                        .build()

                return retrofit.create(LottoInfoApi::class.java)
            }

            data class LottoWebData(
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
            ){
                fun convertToLottData(): LottoData {
                    val balls = arrayListOf(ball1, ball2, ball3, ball4, ball5, ball6)
                    return LottoData(gameNum, drwNoDate, firstAccumamnt, firstWinamnt, firstPrzwnerCo, totSellamnt, balls, bnusBall)
                }
            }
        }

}
