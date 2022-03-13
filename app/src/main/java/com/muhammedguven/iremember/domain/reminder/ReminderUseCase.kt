package com.muhammedguven.iremember.domain.reminder

import com.muhammedguven.iremember.data.reminder.ReminderRepository
import com.muhammedguven.iremember.ui.model.Reminder
import com.muhammedguven.iremember.ui.model.UserCallLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val reminderMapper: ReminderMapper
) {

    fun setAllReminder(callLogs: List<UserCallLog>) {
        val entities = reminderMapper.mapAllToDatabase(callLogs)
        reminderRepository.setAllReminder(entities)
    }

    fun setReminder(reminder: Reminder) {
        val entity = reminderMapper.mapToDatabase(reminder)
        reminderRepository.setReminder(entity)
    }

    fun fetchReminders(): Flow<List<Reminder?>> {
        return reminderRepository.fetchReminders().map {
            reminderMapper.mapFromDatabase(it)
        }.flowOn(Dispatchers.IO)
    }
}