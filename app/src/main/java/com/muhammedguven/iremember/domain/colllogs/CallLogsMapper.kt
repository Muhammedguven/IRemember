package com.muhammedguven.iremember.domain.colllogs

import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.data.local.entity.CallLogsEntity
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import java.util.Date
import javax.inject.Inject

class CallLogsMapper @Inject constructor() {

    fun mapFromResponse(callLogs: List<CallLogsEntity?>?): List<UserCallLog> {
        return callLogs?.mapNotNull { callLogsEntity ->
            UserCallLog(
                id = callLogsEntity?.id.orZero(),
                phoneNumber = callLogsEntity?.phoneNumber.orEmpty(),
                duration = callLogsEntity?.duration.orEmpty(),
                type = callLogsEntity?.type.orEmpty(),
                date = callLogsEntity?.date ?: Date()
            )
        }.orEmpty()
    }

    fun mapToRequest(callLogs: List<UserCallLog>): List<CallLogsEntity> {
        return callLogs.map { callLog ->
            CallLogsEntity(
                id = callLog.id,
                phoneNumber = callLog.phoneNumber,
                duration = callLog.duration,
                type = callLog.type,
                date = callLog.date
            )
        }
    }
}