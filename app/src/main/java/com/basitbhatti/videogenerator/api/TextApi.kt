package com.basitbhatti.videogenerator.api

import com.basitbhatti.videogenerator.model.InitialResponse
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.model.TextRequestStatus
import com.basitbhatti.videogenerator.utils.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TextApi {

    @Headers(
        "x-rapidapi-key: $API_KEY",
        "x-rapidapi-host: runwayml.p.rapidapi.com",
        "Content-Type: application/json"
    )
    @POST("/generate/text")
    suspend fun sendTextRequest(
        @Body textPrompt: TextRequestBody
    ): Response<InitialResponse>

    @Headers(
        "x-rapidapi-key: $API_KEY",
        "x-rapidapi-host: runwayml.p.rapidapi.com",
        "Content-Type: application/json"
    )
    @GET("/status")
    suspend fun getStatus(
        @Query("uuid") uuid: String
    ) : Response<TextRequestStatus>

}