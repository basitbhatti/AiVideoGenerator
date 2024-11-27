package com.basitbhatti.videogenerator.db

import android.content.Context
import androidx.room.RoomDatabase

abstract class RequestDatabase : RoomDatabase() {

    abstract fun dao(): RequestDao

    private var INSTANCE: RequestDatabase? = null

    companion object{
        fun getInstance(context:Context){

        }
    }

}
