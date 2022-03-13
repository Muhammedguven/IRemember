package com.muhammedguven.iremember.domain.callhistories

import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.createreminder.model.Reminder
import com.muhammedguven.iremember.ui.home.model.CallHistory
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import javax.inject.Inject

class CallHistoriesUseCase @Inject constructor(
    private val callHistoriesMapper: CallHistoriesMapper
) {

    fun fetchCallHistories(
        callLogs: List<UserCallLog>,
        contacts: List<Contact>,
        reminders: List<Reminder>
    ): List<CallHistory?> {
        return callHistoriesMapper.mapResponses(callLogs, contacts, reminders)
    }
}