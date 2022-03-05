package com.muhammedguven.iremember.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammedguven.iremember.local.dao.ContactsDao
import com.muhammedguven.iremember.local.entity.ContactsEntity

@Database(
    entities = [
        ContactsEntity::class
    ],
    version = 1
)
abstract class IRememberDatabase : RoomDatabase() {

    abstract val contactsDao: ContactsDao

}