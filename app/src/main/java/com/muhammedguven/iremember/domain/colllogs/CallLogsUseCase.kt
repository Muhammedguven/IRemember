package com.muhammedguven.iremember.domain.colllogs

import com.muhammedguven.iremember.data.calllogs.CallLogsRepository
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CallLogsUseCase @Inject constructor(
    private val callLogsRepository: CallLogsRepository,
    private val callLogsMapper: CallLogsMapper
) {

    fun fetchCallLogs(): Flow<List<UserCallLog>> {
        return callLogsRepository.fetchCallLogs().map { callLogsMapper.mapFromResponse(it) }
            .flowOn(Dispatchers.IO)
    }

    fun updateCallLogs(callLogs: List<UserCallLog>) {
        val newCallLogs = callLogsMapper.mapToResponse(callLogs)
        callLogsRepository.updateCallLogs(newCallLogs)
    }
}