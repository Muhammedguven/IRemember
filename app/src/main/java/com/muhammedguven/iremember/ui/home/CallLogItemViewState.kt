package com.muhammedguven.iremember.ui.home

import com.muhammedguven.iremember.ui.home.model.UserCallLog

class CallLogItemViewState(
    val callLog: UserCallLog
) {

    fun getId() = callLog.id.toString()
}