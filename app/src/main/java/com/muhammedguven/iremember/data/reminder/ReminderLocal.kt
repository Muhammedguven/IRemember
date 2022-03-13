package com.muhammedguven.iremember.data.reminder

import com.muhammedguven.iremember.data.local.dao.ReminderDao
import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderLocal @Inject constructor(
    private val reminderDao: ReminderDao
) {

    fun setAllReminder(reminders: List<ReminderEntity>) {
        for (reminder in reminders) {
            reminderDao.update(
                reminder.phoneNumber,
                reminder.callType,
                reminder.lastCallDate,
                reminder.name
            )
        }
    }

    fun setReminder(reminder: ReminderEntity) {
        reminderDao.insert(reminder)
    }

    fun fetchReminders(): Flow<List<ReminderEntity>> {
        return reminderDao.fetchReminders()
    }
}