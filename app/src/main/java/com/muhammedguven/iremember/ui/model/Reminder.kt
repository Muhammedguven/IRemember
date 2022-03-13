package com.muhammedguven.iremember.ui.model

import java.time.LocalDate

data class Reminder(
    val phoneNumber: String,
    val name: String,
    val lastCallDate: LocalDate,
    val dayInterval: Int,
    val callType: String,
    val isActive: Boolean
)