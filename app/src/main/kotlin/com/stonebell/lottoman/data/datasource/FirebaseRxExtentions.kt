package com.stonebell.lottoman.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.Single


inline fun DatabaseReference.createObservable(): io.reactivex.Observable<DataSnapshot> {
    return Observable.create { emitter ->
        this.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                emitter.onError(RuntimeException(p0.message))
            }

            override fun onDataChange(p0: DataSnapshot) {
                emitter.onNext(p0)
                emitter.onComplete()
            }
        })
    }
}

inline fun DatabaseReference.createSingle(): io.reactivex.Single<DataSnapshot> {
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