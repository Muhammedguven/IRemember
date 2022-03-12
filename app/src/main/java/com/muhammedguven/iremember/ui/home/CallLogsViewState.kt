package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.home.model.UserCallLog

class CallLogsViewState(private val callLogs: List<UserCallLog>) {

    fun getCallLogs(): List<UserCallLog> = callLogs
}