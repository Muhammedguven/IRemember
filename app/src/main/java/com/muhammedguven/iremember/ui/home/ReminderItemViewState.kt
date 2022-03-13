package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.model.Reminder

class ReminderItemViewState(
    val reminder: Reminder
) {

    fun getName(): String = reminder.name

    fun getPhoneNumber(): String = reminder.phoneNumber

    fun getLastCallDate() = reminder.lastCallDate.toString()

    fun getRemainingTime(): String {
        return if (reminder.dayInterval >= 0)
            reminder.dayInterval.toString()
        else
            ""
    }

}