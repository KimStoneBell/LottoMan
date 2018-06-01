package com.stonebell.lottoman

import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import com.stonebell.lottoman.info.LottoData
import com.stonebell.lottoman.web.query.LottoInfoApi
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lotto_info.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LottoInfoActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_info)

        btn_lotto_info.clicks()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .map { callLottoInfo() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { tv_lotto_info.setText(it.toString()) }
    }

    fun callLottoInfo(): Observable<LottoData> {
        return LottoInfoApi.create().getLottoInfoRx(10.toString())
    }
}