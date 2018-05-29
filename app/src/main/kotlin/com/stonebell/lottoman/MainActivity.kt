package com.stonebell.lottoman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RxView.clicks(btn_action)
                .map { "click! id : ${it.toString()}" }
                .subscribe { tv_text2.setText(it) }


        val source = RxTextView.textChanges(et_text)
                .filter { it.isNotEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .map{"serch text : $it"}


        source.observeOn(AndroidSchedulers.mainThread())
                .subscribe { tv_text2.setText(it) }


//        btn_action.setOnClickListener {
//            Observable.just(it)
//                    .map { "click! id : ${it.id}" }
//                    .compose(bindToLifecycle())
//                    .subscribe { tv_text.setText(it) }
//        }
    }
}
