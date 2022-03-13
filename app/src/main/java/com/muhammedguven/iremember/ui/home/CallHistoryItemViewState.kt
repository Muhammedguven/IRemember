package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.home.model.CallHistory

class CallHistoryItemViewState(
    val callHistory: CallHistory
) {

    fun getId() = callHistory.id.toString()

    fun getRemainingTime() = callHistory.remainingTime.toString() + " gün kaldı"
}