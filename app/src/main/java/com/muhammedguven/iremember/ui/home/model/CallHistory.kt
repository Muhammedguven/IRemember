package com.muhammedguven.iremember.ui.home.model

import java.util.Date

data class CallHistory(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val duration: String,
    val type: String,
    val date: Date,
    val remainingTime: Long
)