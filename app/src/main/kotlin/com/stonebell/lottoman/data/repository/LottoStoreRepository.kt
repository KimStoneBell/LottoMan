package com.stonebell.lottoman.data.repository

import com.stonebell.lottoman.domain.ILottoStoreRepository
import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Single
import javax.inject.Inject

class LottoStoreRepository @Inject constructor(var cashDataSource: ILottoStoreSource): ILottoStoreRepository {

    override fun getStoreData(storeId: Int): Single<StoreData> {
        return cashDataSource.getData(storeId)
    }

    override fun getStoreDatas(): Single<List<StoreData>> {
        return cashDataSource.getDatas()
    }
}