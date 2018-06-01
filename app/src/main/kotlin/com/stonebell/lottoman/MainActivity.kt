package com.stonebell.lottoman

import android.content.Intent
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_lotto.clicks()
                .compose(bindToLifecycle())
                .subscribe {
                    startActivity(Intent(this, LottoInfoActivity::class.java))
                }
    }
}
