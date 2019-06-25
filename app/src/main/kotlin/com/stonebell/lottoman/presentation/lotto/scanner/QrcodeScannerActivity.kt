package com.stonebell.lottoman.presentation.lotto.scanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.zxing.Result
import com.stonebell.lottoman.domain.entitiy.CustomerNumberData
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrcodeScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        Log.d("handleResult toString ", rawResult.toString())

        val sourceResult = rawResult.toString()

        val parseResult = sourceResult.substringAfter("?v=")

        parseLottoDatas(parseResult)

        onBackPressed()
    }

    private fun parseLottoDatas(parseData: String): List<CustomerNumberData> {
        val gameNum = parseData.substring(IntRange(0, 3)).toInt()

        for (pos in 4..parseData.length step 13) {

            Log.d("john", "${parseData.substring(pos, pos + 1)}")
            Log.d("john", "${parseData.substring(pos+1, pos+3).toInt()}")
            Log.d("john", "${parseData.substring(pos+3, pos+5).toInt()}")
            Log.d("john", "${parseData.substring(pos+5, pos+7).toInt()}")
            Log.d("john", "${parseData.substring(pos+7, pos+9).toInt()}")
            Log.d("john", "${parseData.substring(pos+9, pos+11).toInt()}")
            Log.d("john", "${parseData.substring(pos+11, pos+13).toInt()}")

            if (!"mq".contains(parseData.substring(pos+13, pos+14))) {
                break
            }
        }

        return ArrayList<CustomerNumberData>()
    }
}