package com.muhammedguven.iremember.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedguven.iremember.data.local.entity.ContactsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ContactsDao {

    @Query("SELECT * FROM contacts ORDER BY contact_name DESC")
    abstract fun fetchContacts(): Flow<List<ContactsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(contacts: List<ContactsEntity>)

}