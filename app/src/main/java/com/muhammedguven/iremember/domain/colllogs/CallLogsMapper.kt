package com.muhammedguven.iremember.domain.colllogs

import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.local.entity.CallLogsEntity
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

    fun mapToResponse(callLogs: List<UserCallLog?>?): List<CallLogsEntity> {
        return callLogs?.mapNotNull { callLog ->
            CallLogsEntity(
                id = callLog?.id.orZero(),
                phoneNumber = callLog?.phoneNumber.orEmpty(),
                duration = callLog?.duration.orEmpty(),
                type = callLog?.type.orEmpty(),
                date = callLog?.date ?: Date()
            )
        }.orEmpty()
    }
}