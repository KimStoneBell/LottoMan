package com.stonebell.lottoman.web.query

import android.os.AsyncTask
import com.google.firebase.database.FirebaseDatabase
import com.stonebell.lottoman.info.StoreData
import org.jsoup.Jsoup

class LottoStoreInfoApi {
    private val htmlPageUrl = "https://www.nlotto.co.kr/store.do?method=topStoreRank&rank=1&pageGubun=L645&nowPage=%d" //파싱할 홈페이지의 URL주소
    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    fun updateLottoStoreInfoToFirebase() {
        doJsoupAsync {
            for (i in 1 .. 180) {
                val doc = Jsoup.connect(String.format(htmlPageUrl, i)).post()
                val rows = doc.select("tr")

                for (row in rows) {
                    val storeData = StoreData(row.child(0).text(),
                            row.child(1).text(),
                            row.child(2).text(),
                            row.child(3).text())

                    database.reference.child("STORES").child(storeData.no).setValue(storeData)
                }
            }
        }.execute()
    }

    inner class doJsoupAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}