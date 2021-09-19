package com.ajailani.pokemon.data.repository;

import androidx.lifecycle.LiveData;

import com.ajailani.pokemon.data.api.ApiService;
import com.ajailani.pokemon.data.db.PokeDao;
import com.ajailani.pokemon.data.model.Pokemon;
import com.ajailani.pokemon.data.model.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MainRepository {
    private PokeDao pokeDao;
    private ApiService apiService;

    @Inject
    public MainRepository(PokeDao pokeDao, ApiService apiService) {
        this.pokeDao = pokeDao;
        this.apiService = apiService;
    }

    public Observable<PokemonResponse> getPokemons() {
        return apiService.getPokemons();
    }

    public void insertFavoritePokemon(Pokemon pokemon) {
        pokeDao.insertFavoritePokemon(pokemon);
    }

    public LiveData<List<Pokemon>> getFavoritePokemons() {
        return pokeDao.getFavoritePokemons();
    }

    public void deleteFavoritePokemon(String pokemonName) {
        pokeDao.deleteFavoritePokemon(pokemonName);
    }

    public void deleteAll() {
        pokeDao.deleteAll();
    }
}