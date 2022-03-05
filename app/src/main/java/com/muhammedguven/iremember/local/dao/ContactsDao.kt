package com.muhammedguven.iremember.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.muhammedguven.iremember.local.entity.ContactsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ContactsDao {

    @Query("SELECT * FROM contacts ORDER BY contact_name DESC")
    abstract fun fetchContacts(): Flow<List<ContactsEntity>>
}