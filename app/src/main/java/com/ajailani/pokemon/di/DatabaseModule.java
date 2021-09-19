package com.ajailani.pokemon.di;

import android.app.Application;

import androidx.room.Room;

import com.ajailani.pokemon.data.db.PokeDao;
import com.ajailani.pokemon.data.db.PokemonDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static PokemonDb providePokemonDb(Application application) {
        return Room.databaseBuilder(application, PokemonDb.class, "FavoriteDb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();
    }

    @Provides
    @Singleton
    public static PokeDao providePokeDao(PokemonDb pokemonDb) {
        return pokemonDb.pokeDao();
    }
}
