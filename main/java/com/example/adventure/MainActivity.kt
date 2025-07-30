package com.example.adventure
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import com.example.adventure.ui.theme.PokemonAdapter
import models.Pokemon
import network.PokemonApiService

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var loadMoreButton: Button
    private val pokemonList = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupRecyclerView()
        setupButtonClickListener()
        loadInitialPokemon()
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.pokemonRecyclerView)
        loadMoreButton = findViewById(R.id.loadMoreButton)
    }

    private fun setupRecyclerView() {
        pokemonAdapter = PokemonAdapter(pokemonList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pokemonAdapter
        }
    }

    private fun setupButtonClickListener() {
        loadMoreButton.setOnClickListener {
            loadMorePokemon()
        }
    }

    private fun loadInitialPokemon() {
        // Load first 5 Pokemon
        repeat(5) {
            fetchRandomPokemon()
        }
    }

    private fun loadMorePokemon() {
        // Load 3 more Pokemon
        repeat(3) {
            fetchRandomPokemon()
        }
    }

    private fun fetchRandomPokemon() {
        PokemonApiService.getRandomPokemon { pokemon ->
            runOnUiThread {
                if (pokemon != null) {
                    // Check if Pokemon already exists in list to avoid duplicates
                    if (!pokemonList.any { it.name == pokemon.name }) {
                        pokemonList.add(pokemon)
                        pokemonAdapter.notifyItemInserted(pokemonList.size - 1)
                    }
                } else {
                    showErrorToast()
                }
            }
        }
    }

    private fun showErrorToast() {
        Toast.makeText(
            this,
            "Failed to load Pokémon. Try again!",
            Toast.LENGTH_SHORT
        ).show()
    }
}