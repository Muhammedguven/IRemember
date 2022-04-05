package com.muhammedguven.iremember.data.reminder

import com.muhammedguven.iremember.data.local.dao.ReminderDao
import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
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

    fun deleteReminder(number: String) {
        val reminder = reminderDao.fetchReminderByNumber(number)
        reminderDao.deleteReminder(reminder)
    }

    fun resetReminder(number: String) {
        var reminder = reminderDao.fetchReminderByNumber(number)
        reminder = reminder.copy(lastCallDate = LocalDate.now())
        reminderDao.insert(reminder)
    }
}