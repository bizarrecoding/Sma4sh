package com.bizarrecoding.sm4sh.database

import android.content.Context
import androidx.room.*

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class SmashDatabase : RoomDatabase() {
    abstract val smashDao: SmashDao
    companion object{
        @Volatile
        private var INSTANCE: SmashDatabase? = null

        fun getInstance(context: Context): SmashDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SmashDatabase::class.java,
                        "smash_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}