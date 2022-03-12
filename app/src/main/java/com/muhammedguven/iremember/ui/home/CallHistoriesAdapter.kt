package com.muhammedguven.iremember.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.iremember.databinding.ItemCallHistoryCardBinding
import com.muhammedguven.iremember.ui.home.model.CallHistory

class CallHistoriesAdapter :
    ListAdapter<CallHistory, CallHistoriesAdapter.CallHistoriesHolder>(DIFF_CALLBACK) {

    var itemClickListener: (CallHistory) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallHistoriesHolder {
        val binding = ItemCallHistoryCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CallHistoriesHolder(binding, itemClickListener)
    }

    class CallHistoriesHolder(
        private val binding: ItemCallHistoryCardBinding,
        private val itemClickListener: (CallHistory) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(callHistory: CallHistory) {
            binding.callHistoryItemViewState = CallHistoryItemViewState(callHistory)

            binding.root.setOnClickListener {
                itemClickListener(callHistory)
            }
        }
    }

    override fun onBindViewHolder(holder: CallHistoriesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CallHistory>() {
            override fun areItemsTheSame(oldItem: CallHistory, newItem: CallHistory) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CallHistory, newItem: CallHistory) =
                oldItem == newItem
        }
    }
}