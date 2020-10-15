package com.example.networking.api

import retrofit2.Call
import retrofit2.http.GET

interface CatFactsService {
    @GET("fact")
    fun getFact():Call<CatFacts>
}