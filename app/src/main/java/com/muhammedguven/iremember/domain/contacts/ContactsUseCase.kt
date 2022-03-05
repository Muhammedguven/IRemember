package com.muhammedguven.iremember.domain.contacts

import com.muhammedguven.iremember.data.contacts.ContactsRepository
import com.muhammedguven.iremember.ui.contacts.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactsUseCase @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val contactsMapper: ContactsMapper
) {

    fun fetchContacts(): Flow<List<Contact>> {
        return contactsRepository.fetchContacts().map { contactsMapper.mapFromResponse(it) }
            .flowOn(Dispatchers.IO)
    }
}