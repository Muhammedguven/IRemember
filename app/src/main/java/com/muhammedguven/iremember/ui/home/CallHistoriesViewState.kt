package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.home.model.CallHistory

class CallHistoriesViewState(private val callHistories: List<CallHistory?>) {

    fun getCallHistories(): List<CallHistory?> = callHistories
}