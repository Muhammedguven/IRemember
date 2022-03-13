package com.muhammedguven.iremember.common.helpers

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }

}