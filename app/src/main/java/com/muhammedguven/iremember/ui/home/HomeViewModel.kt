package com.muhammedguven.iremember.ui.home

import androidx.lifecycle.ViewModel
import com.muhammedguven.iremember.domain.contacts.ContactsUseCase
import com.muhammedguven.iremember.ui.contacts.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactsUseCase: ContactsUseCase
) : ViewModel() {


    fun initializeViewModel() {
    }


    fun setContactsToLocal(contacts: List<Contact>) {
        contactsUseCase.updateContacts(contacts)
    }


}