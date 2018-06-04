package com.stonebell.lottoman

import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
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

    val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_info)

        btn_lotto_info.clicks()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap { callLottoInfo() }
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe {
                    val parsingData = Gson().toJson(it).toString()
                    Log.d("hyuhyu", parsingData)
                    tv_lotto_info.setText(parsingData)

                    database.reference.child("game1").setValue(it)
                }

        database.reference.child("game1").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val data = p0.getValue(LottoData::class.java)
                Log.d("hyuhyu", "print data : $data")

            }

        })


    }

    private fun callLottoInfo(): Observable<LottoData> {
        return LottoInfoApi.create().getLottoInfoRx(10.toString())
    }

//    private fun writeNewLottoIndex(data: LottoData) {
//    // Create new post at /user-posts/$userid/$postid and at
//    // /lottos/$postid simultaneously
//    val key = database.child("posts").push().getKey();
//    Post post = new Post(userId, username, title, body);
//    Map<String, Object> postValues = post.toMap();
//
//    Map<String, Object> childUpdates = new HashMap<>();
//    childUpdates.put("/posts/" + key, postValues);
//    childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//    mDatabase.updateChildren(childUpdates);
//}
}