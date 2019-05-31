package com.stonebell.lottoman.presentation.lotto.serch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.stonebell.lottoman.info.LottoData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class LottoSearchDBViewModel : ViewModel(){

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    val lottoInfoText = MutableLiveData<String>()
    val targetLottoText = MutableLiveData<String>()

    private val disposes = CompositeDisposable()

    fun searchLottoInfo(){
        targetLottoText.value?.let{
            disposes += getLottoInfoToFirebase(it.toInt())
                    .map { "Last Lotto No. : ${it.getValue(LottoData::class.java).toString()}" }
                    .subscribe ({
                        lottoInfoText.postValue(it)
                    },{})
        }
    }

    fun searchLottoLastNumber(){
        disposes += getLastLottoNum()
                .map { it.getValue(Int::class.java).toString() }
                .subscribe ({lottoInfoText.postValue(it)
        },{})
    }


    private fun getLottoInfoToFirebase(targetNo: Int) : Single<DataSnapshot> {
        return database.reference
                .child("GAMES")
                .child("NO_$targetNo")
                .createObserver()
                .subscribeOn(Schedulers.io())
    }

    private fun getLastLottoNum() : Single<DataSnapshot> {
        return database.reference
                .child("LAST_GAME_NO")
                .createObserver()
                .subscribeOn(Schedulers.io())
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

    override fun onCleared() {
        super.onCleared()
        disposes.clear()
    }

}