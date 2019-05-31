package com.stonebell.lottoman.datasource

import com.google.firebase.database.*
import com.stonebell.lottoman.domain.ILottoRepository
import com.stonebell.lottoman.domain.ILottoRepository.Companion.SEARCH_INDEX_LAST
import com.stonebell.lottoman.info.LottoData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LottoRepository : ILottoRepository{

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    override fun searchLottoInfo(searchIndex: Int) : Single<LottoData>  {
        return when(searchIndex){
            SEARCH_INDEX_LAST -> {
                getLastLottoNum()
            }
            else ->{
                getLottoInfoToFirebase(searchIndex)
            }
        }
    }
    override fun searchAllLottoInfo(): Single<List<LottoData>> {
        //TODO 추후 다시만들거임
        return getLastLottoNum().toObservable().toList()
    }

    fun getLottoInfoToFirebase(targetNo: Int) : Single<LottoData> {
        return database.reference
                .child("GAMES")
                .child("NO_$targetNo")
                .createObserver()
                .subscribeOn(Schedulers.io())
                .map{it.getValue(LottoData::class.java)!!}
    }

    fun getLastLottoNum() : Single<LottoData> {
        return database.reference
                .child("LAST_GAME_NO")
                .createObserver()
                .subscribeOn(Schedulers.io())
                .map{it.getValue(LottoData::class.java)!!}
    }

    inline fun DatabaseReference.createObserver(): io.reactivex.Single<DataSnapshot> {
        return Single.create { emitter ->
            this.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(RuntimeException(p0.message))
                }

                override fun onDataChange(p0: DataSnapshot) {
                    emitter.onSuccess(p0)
                }
            })
        }
    }
}