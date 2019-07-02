package com.stonebell.lottoman.presentation.lotto.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.stonebell.lottoman.R
import com.stonebell.lottoman.databinding.ActivityStoreMapBinding
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class StoreMapActivity: AppCompatActivity() {
    private val storeViewModel: StoreMapViewModel by viewModels()

    @Inject
    lateinit var burger : Burger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        burger = Burger(WheatBun(), BeefPatty())
//        DaggerBurgerComponent.builder()
//                .buggerModule(BuggerModule())
//                .build()
//                .inject(this)

        Log.d("hyuhyu", "${burger.bun.getBun()} , ${burger.patty.getPatty()} " )

        DataBindingUtil.setContentView<ActivityStoreMapBinding>(this, R.layout.activity_store_map).apply {
            viewModel = storeViewModel
            lifecycleOwner = this@StoreMapActivity
            lottoContentView.movementMethod = ScrollingMovementMethod()
        }
    }
}

class Burger (val bun : WheatBun, val patty : BeefPatty){
}

class WheatBun{
    fun getBun() : String{
        return "mil bun"
    }
}

class BeefPatty{
    fun getPatty() : String{
        return "cow patty"
    }
}


@Module
class BuggerModule{

    @Provides
    fun provideBurger(bun : WheatBun , patty: BeefPatty) : Burger{
        return Burger(bun, patty)
    }

    @Provides
    fun provideBun() : WheatBun{
        return WheatBun()
    }

    @Provides
    fun providepatty() : BeefPatty{
        return BeefPatty()
    }
}

@Component(modules = [BuggerModule::class])
interface BurgerComponent{
    fun inject(activity : StoreMapActivity)
}
