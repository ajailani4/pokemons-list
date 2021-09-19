package com.ajailani.pokemon.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ajailani.pokemon.data.model.Pokemon;
import com.ajailani.pokemon.data.model.PokemonResponse;
import com.ajailani.pokemon.data.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class PokemonViewModel extends ViewModel {
    private MainRepository mainRepository;
    private MutableLiveData<ArrayList<Pokemon>> pokemons = new MutableLiveData<>();
    private LiveData<List<Pokemon>> favoritePokemons = null;

    @Inject
    public PokemonViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemons() {
        return pokemons;
    }

    public void fetchPokemons() {
        mainRepository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(pokemonResponse -> {
                    ArrayList<Pokemon> pokemons = pokemonResponse.getResults();

                    for (Pokemon pokemon : pokemons) {
                        String url = pokemon.getUrl();
                        String[] indexes = url.split("/");
                        pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+indexes[indexes.length-1] +".png");
                    }

                    return pokemons;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    result -> pokemons.setValue(result),
                    error -> Log.e("Get Pokemons Err: ", error.getMessage())
                );
    }

    public void insertFavoritePokemon(Pokemon pokemon) {
        mainRepository.insertFavoritePokemon(pokemon);
    }

    public LiveData<List<Pokemon>> getFavoritePokemons() {
        return favoritePokemons;
    }

    public void fetchFavoritePokemons() {
        favoritePokemons = mainRepository.getFavoritePokemons();
    }

    public void deleteFavoritePokemon(String pokemonName) {
        mainRepository.deleteFavoritePokemon(pokemonName);
    }

    public void deleteAll() {
        mainRepository.deleteAll();
    }
}
