package com.muhammedguven.iremember.domain.callhistories

import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.home.model.CallHistory
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import javax.inject.Inject

class CallHistoriesUseCase @Inject constructor(
    private val callHistoriesMapper: CallHistoriesMapper
) {

    fun fetchCallHistories(
        callLogs: List<UserCallLog>,
        contacts: List<Contact>
    ): List<CallHistory?> {
        return callHistoriesMapper.mapResponses(callLogs, contacts)
    }
}