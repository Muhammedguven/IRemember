package com.muhammedguven.iremember.ui.createreminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammedguven.iremember.domain.reminder.ReminderUseCase
import com.muhammedguven.iremember.ui.contacts.ContactsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateReminderViewModel @Inject constructor(
    private val useCase: ReminderUseCase
) : ViewModel() {

    private val pageViewStateLiveData: MutableLiveData<ContactsViewState> = MutableLiveData()

    fun getPageViewStateLiveData(): LiveData<ContactsViewState> = pageViewStateLiveData

    fun initializeViewModel() {

    }

    fun setReminder(phoneNumber: String, period: String) {
        viewModelScope.launch {
            useCase
                .setReminder(phoneNumber, period)
        }
    }
}