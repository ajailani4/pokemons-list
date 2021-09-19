package com.ajailani.pokemon.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ajailani.pokemon.data.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 2, exportSchema = false)
public abstract class PokemonDb extends RoomDatabase {
    public abstract PokeDao pokeDao();
}
