package com.basitbhatti.videogenerator.model

data class TextRequestStatus(
    val gif_url: String,
    val progress: Int,
    val status: String,
    val url: String,
    val uuid: String
)