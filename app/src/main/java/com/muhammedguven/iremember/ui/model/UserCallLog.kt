package com.muhammedguven.iremember.ui.model

import java.time.LocalDate

data class UserCallLog(
    val phoneNumber: String,
    val type: String,
    val date: LocalDate
)