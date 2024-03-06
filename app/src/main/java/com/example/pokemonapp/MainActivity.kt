package com.example.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokemonapp.ui.theme.PokemonAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate ViewModel
        mainViewModel = MainViewModel()

        // Load the Pokemon from API
        mainViewModel.updatePokemon("Pikachu")

        setContent {
            PokemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DisplaySelectedPokemon()

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DisplaySelectedPokemon() {
        //
        // Get pokemon from ViewModel, and the UI will re-compose when ViewModel changes or data is loaded
        //

        val pokemon by mainViewModel.pokemonStateFlow.collectAsState()

        // Never used variables
        //val currentPokemon = pokemon?.name
        //val type = pokemon?.pokemonTypes

        //
        // Render UI
        //

        var textFieldLocation by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        // Current Pokemon
                        Text("${pokemon?.name}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                    }
                )
            },
        ) { innerPadding ->

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    // Update
                    TextField(
                        value = textFieldLocation,
                        onValueChange = { textFieldLocation = it },
                        label = { Text("Change Pokemon") }
                    )

                    Button(
                        onClick = {
                            mainViewModel.updatePokemon(textFieldLocation)
                        }
                    ){
                        Text("Update")
                    }

                    LazyColumn{
                        // Pokemon Image
                        item {
                            AsyncImage(model = pokemon?.sprites?.defaultImage, contentDescription = pokemon?.name )
                        }
                        // Pokemon Name Text
                        item {
                            Text("${pokemon?.name}",fontSize = 25.sp)
                        }
                        // Pokemon Weight text
                        item {
                            Text("Weight: ${pokemon?.weight}")
                        }
                        // Pokemon Height text
                        item {
                            Text("Height: ${pokemon?.height}")
                        }
                        // Pokemon Types text
                        item {
                            Text("Types: ")
                            pokemon?.pokemonTypes?.forEach{ pokemonType ->
                                Text("- ${pokemonType.pokemonType.name}")
                            }
                        }
                    }

                }
            }
        }
    }

}
