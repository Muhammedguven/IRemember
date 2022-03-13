package com.muhammedguven.iremember.ui.createreminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.domain.reminder.ReminderUseCase
import com.muhammedguven.iremember.ui.home.RemindersViewState
import com.muhammedguven.iremember.ui.model.Reminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateReminderViewModel @Inject constructor(
    private val useCase: ReminderUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<RemindersViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<RemindersViewState> = pageViewStateLiveData

    fun initializeViewModel() {

    }

    fun setReminder(reminder: Reminder) {
        viewModelScope.launch {
            useCase.setReminder(reminder)
        }
    }
}