package com.stonebell.lottoman

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.*
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.text
import com.stonebell.lottoman.web.query.LottoInfoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lotto_db_maker.*

/**
 * 파이어베이스에 DB쌓을 용도로 일단 만들어봄.
 * DB에 저장할 회차를 입력받음 (시작회차, 끝 회차)
 * 받은내역 Firebase DB에 저장
 */

class LottoDBMakeActivity : AppCompatActivity() {

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    private val disposes = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_db_maker)


        val source = Observable.combineLatest(btn_lotto_info.clicks(), Observable.just(et_start_game_number.text), Observable.just(et_end_game_number.text)
                , Function3 { _:Unit, startNum: CharSequence, endNum: CharSequence -> Pair(startNum.toString(), endNum.toString())})
                .filter { it.first.isNotEmpty() }
                .observeOn(Schedulers.io())
                .share()

        disposes += source.filter { it.second.isNotEmpty() }
                .flatMap { callLottoInfo(it.first.toInt(), it.second.toInt()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val lottoData = it.convertToLottData()
                    database.reference.child("GAMES").child("NO_${lottoData.gameNum}").setValue(lottoData)
                    tv_lotto_info.setText(lottoData.toString())
                            Log.d("hyuhyu", lottoData.toString())
                }

        disposes += source.filter { it.second.isEmpty() }
                .map { it.first }
                .flatMap { callLottoInfo(it.toInt()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val lottoData = it.convertToLottData()
                    database.reference.child("GAMES").child("NO_${lottoData.gameNum}").setValue(lottoData)
                    tv_lotto_info.setText(lottoData.toString())
                    Log.d("hyuhyu", lottoData.toString())
                }
    }

    override fun onDestroy() {
        disposes.dispose()
        super.onDestroy()
    }

    private fun callLottoInfo(startNo: Int, endNo: Int): Observable<LottoInfoApi.Companion.LottoWebData> {
        val infoRange = (startNo..endNo)
        return Observable.fromIterable(infoRange)
                .flatMap { callLottoInfo(it) }
    }

    private fun callLottoInfo(number: Int): Observable<LottoInfoApi.Companion.LottoWebData> {
        return LottoInfoApi.create().getLottoInfoRx(number.toString())
    }
}