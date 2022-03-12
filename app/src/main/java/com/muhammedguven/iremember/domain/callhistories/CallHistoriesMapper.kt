package com.muhammedguven.iremember.domain.callhistories

import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.home.model.CallHistory
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import javax.inject.Inject

class CallHistoriesMapper @Inject constructor() {

    fun mapResponses(callLogs: List<UserCallLog>, contacts: List<Contact>): List<CallHistory?> {
        return callLogs.mapNotNull { callLog ->
            val contact = contacts.find { it.contactPhoneNumber == callLog.phoneNumber }
            if (contact == null) null
            else CallHistory(
                id = callLog.id,
                name = contact.contactName.orEmpty(),
                phoneNumber = contact.contactPhoneNumber.orEmpty(),
                duration = callLog.duration,
                type = callLog.type,
                date = callLog.date
            )
        }
    }
}