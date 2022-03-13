package com.muhammedguven.iremember.data.createreminder

import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderLocal: ReminderDataSource.Local
) {

    fun setReminder(phoneNumber: String, period: String) {
        reminderLocal.setReminder(phoneNumber, period)
    }

    fun fetchReminders(): Flow<List<ReminderEntity>> {
        return reminderLocal.fetchReminders()
    }
}