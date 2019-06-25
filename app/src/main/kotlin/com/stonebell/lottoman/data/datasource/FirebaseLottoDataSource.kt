package com.stonebell.lottoman.data.datasource

import com.google.firebase.database.*
import com.stonebell.lottoman.data.repository.ILottoDataSource
import com.stonebell.lottoman.domain.entitiy.LottoData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FirebaseLottoDataSource : ILottoDataSource{
    override fun getDatas(): Single<List<LottoData>> {
        return database.reference
                .child("GAMES")
                .createObservable()
                .flatMapIterable { it.children }
                .map{it.getValue(LottoData::class.java)!!}.toList()

    }

    override fun getSize(): Single<Int> {
        return database.reference
                .child("GAMES")
                .createSingle()
                .map { it.childrenCount.toInt() }
    }

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    override fun getData(gameRound: Int): Single<LottoData> {
        return database.reference
                .child("GAMES")
                .child("NO_$gameRound")
                .createSingle()
                .subscribeOn(Schedulers.io())
                .map{it.getValue(LottoData::class.java)!!}
    }

    override fun setData(data: LottoData) {
        database.reference
                .child("GAMES")
                .child("NO_${data.gameRound}")
                .setValue(data)
    }
}