package com.muhammedguven.iremember.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calllogs")
class CallLogsEntity constructor(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "call_log_phone_number")
    val phoneNumber: String? = null,
    @ColumnInfo(name = "call_log_duration")
    val duration: String? = null,
    @ColumnInfo(name = "call_log_type")
    val type: String? = null,
    @ColumnInfo(name = "call_log_date")
    val date: Date? = null
)