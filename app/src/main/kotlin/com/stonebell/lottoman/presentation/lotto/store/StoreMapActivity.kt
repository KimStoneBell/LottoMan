package com.stonebell.lottoman.presentation.lotto.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.stonebell.lottoman.R
import com.stonebell.lottoman.databinding.ActivityStoreMapBinding

class StoreMapActivity : AppCompatActivity() {
    private val storeViewModel: StoreMapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityStoreMapBinding>(this, R.layout.activity_store_map).apply {
            viewModel = storeViewModel
            lifecycleOwner = this@StoreMapActivity
            lottoContentView.movementMethod = ScrollingMovementMethod()
        }
    }
}
