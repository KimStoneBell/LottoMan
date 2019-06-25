package com.stonebell.lottoman.data.repository

import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Single

interface ILottoStoreSource {
    fun getData(storeId : Int) : Single<StoreData>
    fun getDatas() : Single<List<StoreData>>
}