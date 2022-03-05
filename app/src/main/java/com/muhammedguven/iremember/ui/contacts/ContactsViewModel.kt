package com.muhammedguven.iremember.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.domain.contacts.ContactsUseCase
import com.muhammedguven.iremember.ui.contacts.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val useCase: ContactsUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<ContactsViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<ContactsViewState> = pageViewStateLiveData

    fun initializeViewModel() {
        fetchContacts()
    }

    fun fetchContacts() {
        viewModelScope.launch {
            useCase
                .fetchContacts()
                .collect {
                    onContactListReady(it)
                }
        }
    }

    private fun onContactListReady(contactList: List<Contact>) {
        val list = listOf<Contact>(
            Contact(1L, "123123123", "Ali can"),
            Contact(1L, "123123123", "Ali can"),
            Contact(1L, "123123123", "Ali can"),
        )
        pageViewStateLiveData.value = ContactsViewState(list)
    }
}