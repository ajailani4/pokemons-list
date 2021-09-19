package com.ajailani.pokemon.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ajailani.pokemon.R;
import com.ajailani.pokemon.databinding.ActivityMainBinding;
import com.ajailani.pokemon.ui.view.fragment.FavoritesFragment;
import com.ajailani.pokemon.ui.view.fragment.HomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isFavoriteListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();

        binding.changeFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavoriteListVisible) {
                    isFavoriteListVisible = false;
                    binding.changeFragmentBtn.setText(getResources().getString(R.string.favorites));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new HomeFragment())
                            .commit();
                }  else {
                    isFavoriteListVisible = true;
                    binding.changeFragmentBtn.setText(getResources().getString(R.string.home));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new FavoritesFragment())
                            .commit();
                }
            }
        });
    }
}