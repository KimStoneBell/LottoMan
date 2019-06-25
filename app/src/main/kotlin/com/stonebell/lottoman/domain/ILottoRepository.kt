package com.stonebell.lottoman.domain

import com.stonebell.lottoman.domain.entitiy.LottoData
import io.reactivex.Single

interface ILottoRepository{
    companion object {
        const val GAME_ROUND_LAST = -1
    }

    fun getLottoData(roundNum : Int = GAME_ROUND_LAST) : Single<LottoData>

    fun getAllLottoData() : Single<List<LottoData>>

    fun addLottoData(lottoData : LottoData)
}