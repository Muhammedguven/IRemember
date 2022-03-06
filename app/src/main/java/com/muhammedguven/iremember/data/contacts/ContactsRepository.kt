package com.muhammedguven.iremember.data.contacts

import com.muhammedguven.iremember.local.entity.ContactsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val contactsLocal: ContactsDataSource.Local
) {

    fun fetchContacts(): Flow<List<ContactsEntity>> {
        return contactsLocal.getContacts()
    }

    fun updateContacts(contacts: List<ContactsEntity>) {
        contactsLocal.updateContacts(contacts)
    }
}