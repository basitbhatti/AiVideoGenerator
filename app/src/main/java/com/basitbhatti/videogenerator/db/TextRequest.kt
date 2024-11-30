package com.basitbhatti.videogenerator.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "request")
class TextRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prompt: String,
    val requestStatus: String,
    val status: String,
    val uuid: String,
    val url: String? = null
)