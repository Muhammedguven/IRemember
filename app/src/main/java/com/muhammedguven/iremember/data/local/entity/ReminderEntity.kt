package com.muhammedguven.iremember.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "reminders")
data class ReminderEntity constructor(
    @PrimaryKey
    val phoneNumber: String,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "lastCallDate")
    val lastCallDate: LocalDate? = null,
    @ColumnInfo(name = "dayInterval")
    val dayInterval: Int? = null,
    @ColumnInfo(name = "callType")
    val callType: String? = null,
    @ColumnInfo(name = "isActive")
    val isActive: Boolean? = null
)