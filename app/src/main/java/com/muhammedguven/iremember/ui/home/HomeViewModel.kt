package com.muhammedguven.iremember.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.domain.colllogs.CallLogsUseCase
import com.muhammedguven.iremember.domain.contacts.ContactsUseCase
import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactsUseCase: ContactsUseCase,
    private val callLogsUseCase: CallLogsUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<CallLogsViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<CallLogsViewState> = pageViewStateLiveData

    fun initializeViewModel() {
        fetchCallLogs()
    }

    fun fetchCallLogs() {
        viewModelScope.launch {
            callLogsUseCase
                .fetchCallLogs()
                .collect {
                    onCallLogsReady(it)
                }
        }
    }

    private fun onCallLogsReady(callLogs: List<UserCallLog>) {
        pageViewStateLiveData.value = CallLogsViewState(callLogs)
    }

    fun setContactsToLocal(contacts: List<Contact>) {
        contactsUseCase.updateContacts(contacts)
    }

    fun setCallLogsToLocal(callLogs: List<UserCallLog>) {
        callLogsUseCase.updateCallLogs(callLogs)
    }


}