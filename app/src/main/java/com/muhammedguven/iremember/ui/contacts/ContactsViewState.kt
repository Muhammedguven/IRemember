package com.muhammedguven.iremember.ui.contacts

import com.muhammedguven.iremember.ui.contacts.model.Contact

class ContactsViewState(private val contacts: List<Contact>) {

    fun getContacts(): List<Contact> = contacts
}