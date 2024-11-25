package com.basitbhatti.videogenerator.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val BASE_URL = "https://runwayml.p.rapidapi.com"

    var INSTANCE : Retrofit? = null

    fun getTextRequestInstance(): Retrofit {
        if (INSTANCE == null){
            synchronized(this){
               INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
        return INSTANCE!!
    }

    val textApi = getTextRequestInstance().create(TextApi::class.java)


}