package com.basitbhatti.videogenerator.api

import com.basitbhatti.videogenerator.model.InitialResponse
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.utils.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TextApi {

    @Headers (
        "x-rapidapi-key: $API_KEY",
        "x-rapidapi-host: runwayml.p.rapidapi.com",
        "Content-Type: application/json"
    )

    @POST("/generate/text")
    suspend fun sendTextRequest(
        @Body textPrompt: TextRequestBody
    ): Response<InitialResponse>

}