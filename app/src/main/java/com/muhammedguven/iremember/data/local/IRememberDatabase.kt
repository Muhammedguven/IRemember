package com.muhammedguven.iremember.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muhammedguven.iremember.common.helpers.Converters
import com.muhammedguven.iremember.data.local.dao.CallLogsDao
import com.muhammedguven.iremember.data.local.dao.ContactsDao
import com.muhammedguven.iremember.data.local.entity.CallLogsEntity
import com.muhammedguven.iremember.data.local.entity.ContactsEntity

@Database(
    entities = [
        ContactsEntity::class,
        CallLogsEntity::class
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class IRememberDatabase : RoomDatabase() {

    abstract val contactsDao: ContactsDao

    abstract val callLogsDao: CallLogsDao

}