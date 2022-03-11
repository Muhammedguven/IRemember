package com.muhammedguven.iremember.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.iremember.databinding.ItemCallLogsCardBinding
import com.muhammedguven.iremember.ui.home.model.UserCallLog

class CallLogsAdapter : ListAdapter<UserCallLog, CallLogsAdapter.CallLogsHolder>(DIFF_CALLBACK) {

    var itemClickListener: (UserCallLog) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogsHolder {
        val binding = ItemCallLogsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CallLogsHolder(binding, itemClickListener)
    }

    class CallLogsHolder(
        private val binding: ItemCallLogsCardBinding,
        private val itemClickListener: (UserCallLog) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(callLog: UserCallLog) {
            binding.callLogItemViewState = CallLogItemViewState(callLog)

            binding.root.setOnClickListener {
                itemClickListener(callLog)
            }
        }
    }

    override fun onBindViewHolder(holder: CallLogsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserCallLog>() {
            override fun areItemsTheSame(oldItem: UserCallLog, newItem: UserCallLog) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserCallLog, newItem: UserCallLog) =
                oldItem == newItem
        }
    }
}