package com.muhammedguven.iremember.data.calllogs

import com.muhammedguven.iremember.local.entity.CallLogsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CallLogsRepository @Inject constructor(
    private val callLogsLocal: CallLogsDataSource.Local
) {

    fun fetchCallLogs(): Flow<List<CallLogsEntity>> {
        return callLogsLocal.getCallLogs()
    }

    fun updateCallLogs(collLogs: List<CallLogsEntity>) {
        callLogsLocal.updateCallLogs(collLogs)
    }
}