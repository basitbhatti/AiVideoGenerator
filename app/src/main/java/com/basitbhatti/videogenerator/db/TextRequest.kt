package com.basitbhatti.videogenerator.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "request")
class TextRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prompt: String,
    var requestStatus: String,
    val status: String,
    val uuid: String,
    var url: String? = null
)