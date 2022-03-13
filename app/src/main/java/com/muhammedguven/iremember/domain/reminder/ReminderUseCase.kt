package com.muhammedguven.iremember.domain.reminder

import com.muhammedguven.iremember.data.createreminder.ReminderRepository
import com.muhammedguven.iremember.ui.createreminder.model.Reminder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository,
) {

    fun setReminder(phoneNumber: String, period: String) {
        reminderRepository.setReminder(phoneNumber, period)
    }

    fun fetchReminders(): Flow<List<Reminder>> {
        return reminderRepository.fetchReminders().map {
            it.mapNotNull { entity ->
                Reminder(
                    phoneNumber = entity.phoneNumber,
                    period = entity.period.orEmpty()
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}