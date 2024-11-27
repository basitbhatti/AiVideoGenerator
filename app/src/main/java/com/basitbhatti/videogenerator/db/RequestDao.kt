package com.basitbhatti.videogenerator.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(item : TextRequest)

    @Delete()
    suspend fun deleteRequest(item : TextRequest)

    @Query("SELECT * FROM request")
    fun getRequests() : LiveData<List<TextRequest>>

}