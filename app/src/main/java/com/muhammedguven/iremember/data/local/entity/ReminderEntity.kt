package com.muhammedguven.iremember.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity constructor(
    @PrimaryKey
    val phoneNumber: String,
    @ColumnInfo(name = "period")
    val period: String? = null
)