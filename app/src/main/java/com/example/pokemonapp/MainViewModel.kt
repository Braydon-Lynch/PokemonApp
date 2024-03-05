package com.example.pokemonapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.retrofit.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _pokemonStateFlow = MutableStateFlow<Pokemon?>(null)

    val pokemonStateFlow: StateFlow<Pokemon?> = _pokemonStateFlow.asStateFlow()

    fun updatePokemon(placeHolder: String) {
        viewModelScope.launch {
            try {
                // Fetch Pokemon data from API
                val pokemon = ApiClient.ApiService.getPokemon(placeHolder)

                // Update ViewModel state
                _pokemonStateFlow.value = pokemon
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }


        }
    }

}