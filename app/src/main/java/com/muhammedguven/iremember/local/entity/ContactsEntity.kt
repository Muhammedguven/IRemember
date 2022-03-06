package com.muhammedguven.iremember.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactsEntity constructor(
    @PrimaryKey
    val contactId: Long? = null,
    @ColumnInfo(name = "contact_phone_number")
    val contactPhoneNumber: String? = null,
    @ColumnInfo(name = "contact_name")
    val contactName: String? = null
)