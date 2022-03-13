package com.muhammedguven.iremember.domain.callhistories

import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.createreminder.model.Reminder
import com.muhammedguven.iremember.ui.home.model.CallHistory
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import java.util.Date
import javax.inject.Inject

class CallHistoriesMapper @Inject constructor() {

    fun mapResponses(
        callLogs: List<UserCallLog>,
        contacts: List<Contact>,
        reminders: List<Reminder>
    ): List<CallHistory?> {
        return callLogs.mapNotNull { callLog ->
            val contact = contacts.find { it.contactPhoneNumber == callLog.phoneNumber }
            val reminder = reminders.find { it.phoneNumber == callLog.phoneNumber }
            if (contact == null || reminder == null) null
            else CallHistory(
                id = callLog.id,
                name = contact.contactName.orEmpty(),
                phoneNumber = contact.contactPhoneNumber.orEmpty(),
                duration = callLog.duration,
                type = callLog.type,
                date = callLog.date,
                remainingTime = getRemainingTime(callLog.date, reminder.period.toLong())
            )
        }
    }

    fun getRemainingTime(addedDate: Date, period: Long): Long {
        // Date objesi java 8 öncesi kullanılıyor Local Date'e çevirelim!!!
        return Date().time.minus(addedDate.time.plus(period.toLong()))
    }
}