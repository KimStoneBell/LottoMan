package com.stonebell.lottoman.presentation.lotto.nummberlist

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stonebell.lottoman.databinding.ActivityLottoWinnerListBinding
import com.stonebell.lottoman.domain.entitiy.LottoData
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.stonebell.lottoman.databinding.ItemWinningNumberBinding


class WinningNumberListActivity : AppCompatActivity(){
    private val winningNumberViewModel: WinningNumberListViewModel by viewModels()
    private lateinit var databinding : ActivityLottoWinnerListBinding
    private val listAdapter = WinningNumberListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding = DataBindingUtil.setContentView<ActivityLottoWinnerListBinding>(this, com.stonebell.lottoman.R.layout.activity_lotto_winner_list).apply {
            viewModel = winningNumberViewModel
            rvList.adapter = listAdapter
        }

        winningNumberViewModel.lottoWinningDatas.observe(this, Observer {
            listAdapter.submitList(it)
        })
    }
}

class WinningNumberListAdapter : ListAdapter<LottoData, WinningNumberViewHolder>(ListItemCallback()) {

    class ListItemCallback : DiffUtil.ItemCallback<LottoData>() {
        override fun areItemsTheSame(oldItem: LottoData, newItem: LottoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LottoData, newItem: LottoData): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinningNumberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<ItemWinningNumberBinding>(layoutInflater, com.stonebell.lottoman.R.layout.item_winning_number, parent, false)
        return WinningNumberViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WinningNumberViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }
}

class WinningNumberViewHolder(val itemBinding: ItemWinningNumberBinding) : RecyclerView.ViewHolder(itemBinding.root){

    fun binding(item : LottoData){
        itemBinding.apply {
            model = item
        }.executePendingBindings()
    }
}