package com.muhammedguven.iremember.data.calllogs

import com.muhammedguven.iremember.local.entity.CallLogsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CallLogsDataSource {

    class Local @Inject constructor(private val callLogsLocal: CallLogsLocal) {

        fun getCallLogs(): Flow<List<CallLogsEntity>> {
            return callLogsLocal.getCallLogs()
        }

        fun updateCallLogs(callLogs: List<CallLogsEntity>) {
            callLogsLocal.updateCallLogs(callLogs)
        }
    }
}