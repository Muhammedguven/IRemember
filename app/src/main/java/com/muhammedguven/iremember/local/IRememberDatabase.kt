package com.muhammedguven.iremember.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muhammedguven.iremember.common.helpers.Converters
import com.muhammedguven.iremember.local.dao.CallLogsDao
import com.muhammedguven.iremember.local.dao.ContactsDao
import com.muhammedguven.iremember.local.entity.CallLogsEntity
import com.muhammedguven.iremember.local.entity.ContactsEntity

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