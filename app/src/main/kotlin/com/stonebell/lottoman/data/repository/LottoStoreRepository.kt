package com.stonebell.lottoman.data.repository

import com.stonebell.lottoman.data.datasource.FirebaseLottoStoreDataSource
import com.stonebell.lottoman.domain.ILottoStoreRepository
import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Single

class LottoStoreRepository : ILottoStoreRepository {

    private val cashDataSource: ILottoStoreSource by lazy { FirebaseLottoStoreDataSource() }

    override fun getStoreData(storeId: Int): Single<StoreData> {
        return cashDataSource.getData(storeId)
    }

    override fun getStoreDatas(): Single<List<StoreData>> {
        return cashDataSource.getDatas()
    }
}