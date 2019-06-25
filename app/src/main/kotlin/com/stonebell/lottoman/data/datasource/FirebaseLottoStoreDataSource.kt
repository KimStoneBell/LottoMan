package com.stonebell.lottoman.data.datasource

import com.google.firebase.database.FirebaseDatabase
import com.stonebell.lottoman.data.repository.ILottoStoreSource
import com.stonebell.lottoman.domain.entitiy.StoreData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FirebaseLottoStoreDataSource : ILottoStoreSource{

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }


    override fun getData(storeId: Int): Single<StoreData> {
        return database.reference.child("STORES")
                //TODO filter store id
                .child("id_$storeId").createSingle()
                .subscribeOn(Schedulers.io())
                .map{it.getValue(StoreData::class.java)!!}
    }

    override fun getDatas(): Single<List<StoreData>> {
        return database.reference.child("STORES").createObservable().flatMapIterable { it.children }
                .map{it.getValue(StoreData::class.java)!!}.toList()
    }

}