package com.muhammedguven.iremember.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedguven.iremember.local.entity.CallLogsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CallLogsDao {

    @Query("SELECT * FROM calllogs ORDER BY call_log_date DESC")
    abstract fun fetchCallLogs(): Flow<List<CallLogsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(callLogs: List<CallLogsEntity>)

}