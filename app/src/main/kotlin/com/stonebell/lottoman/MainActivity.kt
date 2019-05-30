package com.stonebell.lottoman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 1. 최초 FirebaseDB에서 마지막 회차에 대한 데이터를 가져옴
 * 2. 마지막회차에 대한 데이터로드 후 화면에 보여줌.?
 * 3. 전체 회차에 대한 데이터는 로컬에 한번에 다 받아서 보관? 필요할 때 받아서 보관?
 * 4.
 */

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposables += btn_save_lotto_number.clicks()
                .subscribe {
                    startActivity(Intent(this, LottoDBMakeActivity::class.java))
                }

        disposables += btn_serch_lotto_db.clicks()
                .subscribe {
                    startActivity(Intent(this, LottoSearchDBActivity::class.java))
                }

        disposables += btn_qrcode_scan.clicks()
                .subscribe {
                    startActivity(Intent(this, QrcodeScannerActivity::class.java))
                }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun clickCreate(): Observable<View> {
        return Observable.create{emitter ->
            btn_save_lotto_number.setOnClickListener{
                emitter.onNext(it)
            }
        }
    }
}
