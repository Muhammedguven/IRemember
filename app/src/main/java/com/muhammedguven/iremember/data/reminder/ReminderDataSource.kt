package com.muhammedguven.iremember.data.reminder

import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderDataSource {

    class Local @Inject constructor(private val reminderLocal: ReminderLocal) {

        fun setAllReminder(reminders: List<ReminderEntity>) {
            reminderLocal.setAllReminder(reminders)
        }

        fun setReminder(reminderEntity: ReminderEntity) {
            reminderLocal.setReminder(reminderEntity)
        }

        fun fetchReminders(): Flow<List<ReminderEntity>> {
            return reminderLocal.fetchReminders()
        }
    }
}