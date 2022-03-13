package com.muhammedguven.iremember.data.createreminder

import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderDataSource {

    class Local @Inject constructor(private val reminderLocal: ReminderLocal) {

        fun setReminder(phoneNumber: String, period: String) {
            reminderLocal.setReminder(phoneNumber, period)
        }

        fun fetchReminders(): Flow<List<ReminderEntity>> {
            return reminderLocal.fetchReminders()
        }
    }
}