package com.muhammedguven.iremember.domain.contacts

import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.local.entity.ContactsEntity
import com.muhammedguven.iremember.ui.contacts.model.Contact
import javax.inject.Inject

class ContactsMapper @Inject constructor() {

    fun mapFromResponse(contacts: List<ContactsEntity?>?): List<Contact> {
        return contacts?.mapNotNull { contactsEntity ->
            Contact(
                id = contactsEntity?.contactId.orZero(),
                contactPhoneNumber = contactsEntity?.contactPhoneNumber.orEmpty(),
                contactName = contactsEntity?.contactName.orEmpty()
            )
        }.orEmpty()
    }
}