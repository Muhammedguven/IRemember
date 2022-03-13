package com.muhammedguven.iremember.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ReminderDao {

    @Query("SELECT * FROM reminders ORDER BY phoneNumber DESC")
    abstract fun fetchReminders(): Flow<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: ReminderEntity)

}