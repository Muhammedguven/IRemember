package com.muhammedguven.iremember.data.calllogs

import com.muhammedguven.iremember.data.local.dao.CallLogsDao
import com.muhammedguven.iremember.data.local.entity.CallLogsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CallLogsLocal @Inject constructor(
    private val callLogsDao: CallLogsDao
) {

    fun getCallLogs(): Flow<List<CallLogsEntity>> {
        return callLogsDao.fetchCallLogs()
    }

    fun updateCallLogs(callLogs: List<CallLogsEntity>) {
        callLogsDao.insertAll(callLogs)
    }

}