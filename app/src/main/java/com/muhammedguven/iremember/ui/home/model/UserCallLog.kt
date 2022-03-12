package com.muhammedguven.iremember.ui.home.model

import java.util.Date

data class UserCallLog(
    val id: Int,
    val phoneNumber: String,
    val duration: String,//sec
    val type: String,
    val date: Date
)