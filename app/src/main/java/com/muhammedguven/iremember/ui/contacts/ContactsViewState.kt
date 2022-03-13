package com.muhammedguven.iremember.ui.contacts

import com.muhammedguven.iremember.ui.model.Contact

data class ContactsViewState(private val contacts: List<Contact>) {

    fun getContacts() = contacts
}