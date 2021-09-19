package com.ajailani.pokemon.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajailani.pokemon.data.model.Pokemon;
import com.ajailani.pokemon.databinding.ItemPokemonBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.ViewHolder> {
    private ItemPokemonBinding binding = null;
    private ArrayList<Pokemon> pokemons = new ArrayList<>();

    public PokemonsAdapter(ArrayList<Pokemon> pokemonsList) {
        this.pokemons = pokemonsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(ItemPokemonBinding binding) {
            super(binding.getRoot());
        }

        public void bind(Pokemon pokemon) {
            Glide.with(binding.photo.getContext())
                .load(pokemon.getUrl())
                .into(binding.photo);

            binding.name.setText(pokemon.getName());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public Pokemon getPokemonAt(int position) {
        return pokemons.get(position);
    }
}
