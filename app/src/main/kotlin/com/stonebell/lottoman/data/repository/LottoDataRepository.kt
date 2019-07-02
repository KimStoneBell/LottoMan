package com.stonebell.lottoman.data.repository

import com.stonebell.lottoman.domain.ILottoRepository
import com.stonebell.lottoman.domain.ILottoRepository.Companion.GAME_ROUND_LAST
import com.stonebell.lottoman.domain.entitiy.LottoData
import io.reactivex.Single
import javax.inject.Inject

class LottoDataRepository @Inject constructor(var cashDataSource: ILottoDataSource): ILottoRepository{

    override fun getLottoData(roundNum : Int): Single<LottoData> {
        return when(roundNum){
            GAME_ROUND_LAST -> {
                cashDataSource.getSize().flatMap { cashDataSource.getData(it) }
            }
            else ->{
                cashDataSource.getData(roundNum)
            }
        }
    }

    override fun getAllLottoData(): Single<List<LottoData>> {
        return cashDataSource.getDatas()
    }


    override fun addLottoData(lottoData: LottoData) {
        cashDataSource.setData(lottoData)
    }
}