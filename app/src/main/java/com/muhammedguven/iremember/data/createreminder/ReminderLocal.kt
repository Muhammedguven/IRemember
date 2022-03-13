package com.muhammedguven.iremember.data.createreminder

import com.muhammedguven.iremember.data.local.dao.ReminderDao
import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderLocal @Inject constructor(
    private val reminderDao: ReminderDao
) {

    fun setReminder(phoneNumber: String, period: String) {
        reminderDao.insert(ReminderEntity(phoneNumber = phoneNumber, period = period))
    }

    fun fetchReminders(): Flow<List<ReminderEntity>> {
        return reminderDao.fetchReminders()
    }
}