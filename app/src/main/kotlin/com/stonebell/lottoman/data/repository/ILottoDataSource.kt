package com.stonebell.lottoman.data.repository

import com.stonebell.lottoman.domain.entitiy.LottoData
import io.reactivex.Single

interface ILottoDataSource {
    fun getData(roundNum : Int) : Single<LottoData>
    fun setData(data : LottoData)
    fun getDatas() : Single<List<LottoData>>
    fun getSize() : Single<Int>
}