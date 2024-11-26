package com.basitbhatti.videogenerator.model

data class TextRequestBody(
    val text_prompt: String,
    val model: String = "gen3",
    val width : Int = 1344,
    val height: Int = 768,
    val motion: Int = 5,
    val seed: Int = 0,
    val callback_url: String = "",
    val time: Int = 5
)