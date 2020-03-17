package com.bizarrecoding.sm4sh.database

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(dateLong: Long?): Date?{
        return dateLong?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    /*
    @TypeConverter
    fun fromBoolean(bool: Boolean?): Int?{
        return bool?.let {
            if (it) 1 else 0
        }
    }

    @TypeConverter
    fun fromInt(int: Int?): Boolean? {
        return int?.let {
            it!=0
        }
    }*/
}