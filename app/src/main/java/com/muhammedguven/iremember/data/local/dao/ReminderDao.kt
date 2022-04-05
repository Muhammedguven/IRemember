package com.muhammedguven.iremember.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedguven.iremember.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
abstract class ReminderDao {

    @Query("SELECT * FROM reminders ORDER BY name DESC")
    abstract fun fetchReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE phoneNumber = :phoneNumber")
    abstract fun fetchReminderByNumber(phoneNumber: String): ReminderEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: ReminderEntity)

    @Query("UPDATE reminders SET lastCallDate=:lastCallDate, callType=:callType, name=:name WHERE phoneNumber = :phoneNumber")
    abstract fun update(
        phoneNumber: String,
        callType: String?,
        lastCallDate: LocalDate?,
        name: String?
    )

    @Delete
    abstract fun deleteReminder(reminder: ReminderEntity)

}