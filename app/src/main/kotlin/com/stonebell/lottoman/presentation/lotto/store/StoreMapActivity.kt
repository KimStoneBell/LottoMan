package com.stonebell.lottoman.presentation.lotto.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.google.firebase.database.*
import com.jakewharton.rxbinding2.view.clicks
import com.stonebell.lottoman.R
import com.stonebell.lottoman.info.StoreData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_store_map.*


class StoreMapActivity : AppCompatActivity() {

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    private val disposes = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_map)

        lotto_content_view?.movementMethod = ScrollingMovementMethod() //스크롤 가능한 텍스트뷰로 만들기

        disposes += btn_store_data.clicks()
                .observeOn(Schedulers.io())
                .flatMap { getLottoStoresToFirebase() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    var storeDataStr = ""

                    for (postSnapshot in it.children) {
                        Log.d("johnkim", "store.key : ${postSnapshot.key}")
                        val storeObject = postSnapshot.getValue(StoreData::class.java)
                        Log.d("johnkim", "store.storeName : ${storeObject?.toString()}")
                    }

                    lotto_content_view.text = storeDataStr
                }

    }

    private fun getLottoStoresToFirebase() : Observable<DataSnapshot>{
        return database.reference.child("STORES").createObserver()
    }

    private inline fun DatabaseReference.createObserver(): Observable<DataSnapshot> {
        return Observable.create { emitter ->
            this.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(RuntimeException(p0.message))
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("johnkim", dataSnapshot.childrenCount.toString())
                    emitter.onNext(dataSnapshot)
                }
            })
        }
    }

    override fun onDestroy() {
        disposes.dispose()
        super.onDestroy()
    }


}
