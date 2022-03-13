package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.model.Reminder

class RemindersViewState(private val reminders: List<Reminder?>) {

    fun getReminders(): List<Reminder?> = reminders
}