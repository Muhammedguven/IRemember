package com.muhammedguven.iremember.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.iremember.databinding.ItemContactCardBinding
import com.muhammedguven.iremember.ui.contacts.model.Contact

class ContactsAdapter :
    ListAdapter<Contact, ContactsAdapter.ContactsHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Contact) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
        val binding = ItemContactCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactsHolder(binding, itemClickListener)
    }

    class ContactsHolder(
        private val binding: ItemContactCardBinding,
        private val itemClickListener: (Contact) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.contactItemViewState = ContactItemViewState(contact)

            binding.root.setOnClickListener {
                itemClickListener(contact)
            }
        }
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem == newItem
        }
    }
}