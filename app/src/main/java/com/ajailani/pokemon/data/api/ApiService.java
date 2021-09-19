package com.ajailani.pokemon.data.api;

import com.ajailani.pokemon.data.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
