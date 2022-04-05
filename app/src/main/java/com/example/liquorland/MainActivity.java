package com.example.liquorland;

import android.os.Bundle;

import com.example.liquorland.Adapter.DrinksAdapter;
import com.example.liquorland.Models.Drink;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.liquorland.databinding.ActivityMainBinding;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private Box<Drink> DrinksDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_brands, R.id.navigation_cart, R.id.navigation_checkout, R.id.itemDetailFragment,
                R.id.navigation_orders, R.id.navigation_product_form)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


         }

}