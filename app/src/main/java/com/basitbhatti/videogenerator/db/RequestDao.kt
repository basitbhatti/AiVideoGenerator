package com.basitbhatti.videogenerator.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(item : TextRequest)

    @Update
    suspend fun updateRequest(item : TextRequest)

    @Delete()
    suspend fun deleteRequest(item : TextRequest)

    @Query("SELECT * FROM request")
    fun getRequests() : List<TextRequest>

}