package com.muhammedguven.iremember.data.contacts

import com.muhammedguven.iremember.local.dao.ContactsDao
import com.muhammedguven.iremember.local.entity.ContactsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsLocal @Inject constructor(
    private val contactsDao: ContactsDao
) {

    fun getContacts(): Flow<List<ContactsEntity>> {
        return contactsDao.fetchContacts()
    }

    fun updateContacts(contacts: List<ContactsEntity>) {
        contactsDao.insertAll(contacts)
    }

}