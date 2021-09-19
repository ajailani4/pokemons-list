package com.ajailani.pokemon.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajailani.pokemon.data.model.Pokemon;
import com.ajailani.pokemon.databinding.FragmentHomeBinding;
import com.ajailani.pokemon.ui.adapter.PokemonsAdapter;
import com.ajailani.pokemon.ui.viewmodel.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private PokemonViewModel viewModel;
    private PokemonsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        binding.progressBar.setVisibility(View.VISIBLE);
        setUpItemTouchHelper();
        observeData();
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Pokemon pokemon = adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.insertFavoritePokemon(pokemon);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Pokemon added to favorites", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.pokemonsRv);
    }

    private void observeData() {
        viewModel.fetchPokemons();
        viewModel.getPokemons().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                binding.progressBar.setVisibility(View.GONE);

                adapter = new PokemonsAdapter(pokemons);
                binding.pokemonsRv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.pokemonsRv.setAdapter(adapter);
            }
        });
    }
}
