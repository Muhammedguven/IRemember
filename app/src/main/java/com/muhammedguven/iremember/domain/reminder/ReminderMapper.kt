package com.muhammedguven.iremember.domain.reminder

import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import com.muhammedguven.iremember.ui.model.Reminder
import com.muhammedguven.iremember.ui.model.UserCallLog
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

class ReminderMapper @Inject constructor() {

    fun mapToDatabase(reminder: Reminder): ReminderEntity {
        return ReminderEntity(
            phoneNumber = reminder.phoneNumber,
            name = reminder.name,
            lastCallDate = reminder.lastCallDate,
            dayInterval = reminder.dayInterval,
            isActive = reminder.isActive
        )
    }

    fun mapAllToDatabase(callLogs: List<UserCallLog>): List<ReminderEntity> {
        return callLogs.map { reminder ->
            ReminderEntity(
                phoneNumber = reminder.phoneNumber,
                lastCallDate = reminder.date,
                callType = reminder.type
            )
        }
    }

    fun mapFromDatabase(entities: List<ReminderEntity?>?): List<Reminder?> {
        return entities?.mapNotNull { entity ->
            if (getRemainingTime(entity?.dayInterval, entity?.lastCallDate) < 0) {
                null
            } else {
                Reminder(
                    phoneNumber = entity?.phoneNumber.orEmpty(),
                    name = entity?.name.orEmpty(),
                    lastCallDate = entity?.lastCallDate ?: LocalDate.now(),
                    dayInterval = getRemainingTime(entity?.dayInterval, entity?.lastCallDate),
                    callType = entity?.callType.orEmpty(),
                    isActive = entity?.isActive ?: false
                )
            }
        }.orEmpty()
    }

    private fun getRemainingTime(interval: Int?, lastCallDate: LocalDate?): Int {
        return if (interval != null && lastCallDate != null) {
            val now = LocalDate.now()
            val period = Period.between(lastCallDate, now).days
            (interval - period)
        } else {
            -1
        }

    }
}