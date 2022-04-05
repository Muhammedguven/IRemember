package com.muhammedguven.iremember.ui.reminderactions

import androidx.lifecycle.ViewModel
import com.muhammedguven.iremember.domain.reminder.ReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderActionsViewModel @Inject constructor(
    private val reminderUseCase: ReminderUseCase,
) : ViewModel() {


    fun deleteReminder(number: String) {
        reminderUseCase.deleteReminder(number)
    }

    fun resetReminder(number: String) {
        reminderUseCase.resetReminder(number)
    }
}