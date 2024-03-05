package com.example.pokemonapp.retrofit

import com.example.pokemonapp.models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// This interface contains the individual API REST calls we can make

interface ApiService {

    @GET("pokemon/{name}")
    suspend fun getPokemon (
        @Path("name") name: String
    ): Pokemon

}