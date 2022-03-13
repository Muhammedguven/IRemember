package com.muhammedguven.iremember.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.domain.reminder.ReminderUseCase
import com.muhammedguven.iremember.ui.model.Reminder
import com.muhammedguven.iremember.ui.model.UserCallLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reminderUseCase: ReminderUseCase,
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<RemindersViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<RemindersViewState> = pageViewStateLiveData

    fun initializeViewModel() {
        fetchCallLogs()
    }

    fun fetchCallLogs() {
        viewModelScope.launch {
            reminderUseCase
                .fetchReminders()
                .collect {
                    onCallHistoriesReady(it)
                }
        }
    }

    private fun onCallHistoriesReady(reminder: List<Reminder?>) {
        if (reminder.isNotEmpty()) {
            pageViewStateLiveData.value = RemindersViewState(reminder)
        }
    }

    fun setCallLogsToLocal(callLogs: List<UserCallLog>) {
        reminderUseCase.setAllReminder(callLogs)
    }


}