package com.stonebell.lottoman

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.*
import com.jakewharton.rxbinding2.view.clicks
import com.stonebell.lottoman.info.LottoData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lotto_serch_db.*

class LottoSerchDBActivity: AppCompatActivity(){

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val disposes = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_serch_db)

        val source = Observable.combineLatest(btn_serch_lotto_info.clicks(), Observable.just(et_target_lotto_num.text)
                , BiFunction { _:Unit, startNum: CharSequence -> startNum.toString()})
                .filter { it.isNotEmpty() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())

        disposes += source.flatMap { getLottoInfoToFirbase(it.toInt()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    tv_lotto_info.setText(it.getValue(LottoData::class.java).toString())
                }

        disposes += btn_serch_last_lotto_info.clicks()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap { getLastLottoNum() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    tv_lotto_info.setText("Last Lotto No. : ${it.getValue(Int::class.java).toString()}")
                }
    }

    private fun getLottoInfoToFirbase(targetNo: Int) : Observable<DataSnapshot>{
        return database.reference.child("GAMES").child("NO_$targetNo").create()
    }

    private fun getLastLottoNum() : Observable<DataSnapshot>{
        return database.reference.child("LAST_GAME_NO").create()
    }

    inline fun DatabaseReference.create(): io.reactivex.Observable<DataSnapshot> {
        return Observable.create {emitter ->
            this.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(RuntimeException(p0.message))
                }

                override fun onDataChange(p0: DataSnapshot) {
                    emitter.onNext(p0)
                }
            })
        }
     }

}