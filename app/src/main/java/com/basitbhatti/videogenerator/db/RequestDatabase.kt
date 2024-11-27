package com.basitbhatti.videogenerator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TextRequest::class], version = 1)
abstract class RequestDatabase : RoomDatabase() {

    abstract fun dao(): RequestDao

    companion object {
        lateinit var INSTANCE: RequestDatabase

        fun getInstance(context: Context): RequestDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RequestDatabase::class.java,
                        "RequestsDB"
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }

}
