package com.example.adventure.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adventure.R
import models.Pokemon

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.itemPokemonImage)
        val pokemonName: TextView = itemView.findViewById(R.id.itemPokemonName)
        val pokemonDetails: TextView = itemView.findViewById(R.id.itemPokemonDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        // Load Pokemon image using Glide
        Glide.with(holder.itemView.context)
            .load(pokemon.sprites.front_default)
            //.placeholder(R.drawable.ic_placeholder) // You can add a placeholder drawable
            .into(holder.pokemonImage)

        // Set Pokemon name (capitalize first letter)
        holder.pokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Set Pokemon details
        holder.pokemonDetails.text = buildPokemonDetailsString(pokemon)
    }

    override fun getItemCount(): Int = pokemonList.size

    private fun buildPokemonDetailsString(pokemon: Pokemon): String {
        val types = pokemon.types.joinToString(", ") { it.type.name.replaceFirstChar { char -> char.uppercase() } }
        return """
            Height: ${pokemon.height / 10.0} m
            Weight: ${pokemon.weight / 10.0} kg
            Type(s): $types
        """.trimIndent()
    }
}