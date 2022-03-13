package com.muhammedguven.iremember.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.common.extensions.zip
import com.muhammedguven.iremember.domain.callhistories.CallHistoriesUseCase
import com.muhammedguven.iremember.domain.colllogs.CallLogsUseCase
import com.muhammedguven.iremember.domain.contacts.ContactsUseCase
import com.muhammedguven.iremember.domain.reminder.ReminderUseCase
import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.home.model.CallHistory
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactsUseCase: ContactsUseCase,
    private val callLogsUseCase: CallLogsUseCase,
    private val reminderUseCase: ReminderUseCase,
    private val callHistoriesUseCase: CallHistoriesUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<CallHistoriesViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<CallHistoriesViewState> = pageViewStateLiveData

    fun initializeViewModel() {
        fetchCallLogs()
    }

    fun fetchCallLogs() {
        viewModelScope.launch {
            val callLogs = callLogsUseCase.fetchCallLogs()
            val contacts = contactsUseCase.fetchContacts()
            val reminders = reminderUseCase.fetchReminders()

            callLogs.zip(contacts, reminders) { callLog, contact, reminder ->
                return@zip callHistoriesUseCase.fetchCallHistories(callLog, contact, reminder)
            }.collect { onCallHistoriesReady(it) }
        }
    }

    private fun onCallHistoriesReady(callHistory: List<CallHistory?>) {
        if (callHistory != null) {
            pageViewStateLiveData.value = CallHistoriesViewState(callHistory)
        }
    }

    fun setContactsToLocal(contacts: List<Contact>) {
        contactsUseCase.updateContacts(contacts)
    }

    fun setCallLogsToLocal(callLogs: List<UserCallLog>) {
        callLogsUseCase.updateCallLogs(callLogs)
    }


}