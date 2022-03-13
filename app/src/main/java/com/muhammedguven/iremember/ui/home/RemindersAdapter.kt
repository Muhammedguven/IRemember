package com.muhammedguven.iremember.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.iremember.databinding.ItemReminderCardBinding
import com.muhammedguven.iremember.ui.model.Reminder

class RemindersAdapter :
    ListAdapter<Reminder, RemindersAdapter.CallHistoriesHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Reminder) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallHistoriesHolder {
        val binding = ItemReminderCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CallHistoriesHolder(binding, itemClickListener)
    }

    class CallHistoriesHolder(
        private val binding: ItemReminderCardBinding,
        private val itemClickListener: (Reminder) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(callHistory: Reminder) {
            binding.reminderItemViewState = ReminderItemViewState(callHistory)

            binding.root.setOnClickListener {
                itemClickListener(callHistory)
            }
        }
    }

    override fun onBindViewHolder(holder: CallHistoriesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Reminder>() {
            override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder) =
                oldItem.phoneNumber == newItem.phoneNumber

            override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder) =
                oldItem == newItem
        }
    }
}