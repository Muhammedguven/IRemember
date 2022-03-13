package com.muhammedguven.iremember.ui.contacts

import com.muhammedguven.iremember.ui.model.Contact

class ContactItemViewState(private val contact: Contact) {

    fun getName() = contact.contactName

    fun getPhoneNumber() = contact.contactPhoneNumber
}