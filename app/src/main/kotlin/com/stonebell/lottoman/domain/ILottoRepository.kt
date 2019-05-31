package com.stonebell.lottoman.domain

import com.stonebell.lottoman.info.LottoData
import io.reactivex.Single

interface ILottoRepository{
    companion object {
        const val SEARCH_INDEX_LAST = 0
        const val SEARCH_INDEX_ALL = 1
    }

    fun searchLottoInfo(searchIndex : Int = SEARCH_INDEX_ALL) : Single<LottoData>

    fun searchAllLottoInfo() : Single<List<LottoData>>
}