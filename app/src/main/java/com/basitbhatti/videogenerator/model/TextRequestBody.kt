package com.basitbhatti.videogenerator.model

data class TextRequestBody(
    val text_prompt: String,
    val model: String,
    val width: String,
    val height: String,
    val motion: String,
    val seed: String,
    val callback_url: String,
    val time: String
)