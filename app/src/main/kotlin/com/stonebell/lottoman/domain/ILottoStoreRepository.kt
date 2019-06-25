package com.stonebell.lottoman.domain

import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Single

interface ILottoStoreRepository{
    fun getStoreData(storeId : Int) : Single<StoreData>

    fun getStoreDatas() : Single<List<StoreData>>
}