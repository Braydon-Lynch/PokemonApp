package com.example.pokemonapp.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val weight: Int,
    val height: Int,
    val pokemonTypes: List<PokemonTypes>,
    val sprites: Sprites
)

data class PokemonTypes(
    @SerializedName("type") val pokemonType: PokemonType
)

data class PokemonType(
    val name: String
)

data class Sprites(
    @SerializedName("front_default") val defaultImage: String
)

