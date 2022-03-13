package com.muhammedguven.iremember.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedguven.iremember.ui.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor() : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<ContactsViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<ContactsViewState> = pageViewStateLiveData

    fun initializeViewModel(contacts: List<Contact>) {
        fetchContacts(contacts)
    }

    private fun fetchContacts(contacts: List<Contact>) {
        pageViewStateLiveData.value = ContactsViewState(contacts)
    }

}