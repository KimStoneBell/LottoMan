package com.stonebell.lottoman.web.query

import com.google.gson.annotations.SerializedName
import com.stonebell.lottoman.info.LottoData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LottoInfoApi{
    @GET("common.do")
    fun getLottoInfoRx(@Query("drwNo") gameNumber: String, @Query("method") method: String = "getLottoNumber"): Observable<LottoData>

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
    }
}