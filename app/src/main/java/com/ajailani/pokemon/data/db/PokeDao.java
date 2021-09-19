package com.ajailani.pokemon.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ajailani.pokemon.data.model.Pokemon;

import java.util.List;

@Dao
public interface PokeDao {
    @Insert
    void insertFavoritePokemon(Pokemon pokemon);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Pokemon>> getFavoritePokemons();

    @Query("DELETE FROM favorite_table WHERE name = :pokemonName")
    void deleteFavoritePokemon(String pokemonName);

    @Query("DELETE FROM favorite_table")
    void deleteAll();
}
