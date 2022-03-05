package com.muhammedguven.iremember.data.contacts

import com.muhammedguven.iremember.local.entity.ContactsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsDataSource {

    class Local @Inject constructor(private val contactsLocal: ContactsLocal) {

        fun getContacts(): Flow<List<ContactsEntity>> {
            return contactsLocal.getContacts()
        }
    }
}