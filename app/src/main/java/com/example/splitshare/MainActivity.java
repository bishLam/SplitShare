package com.example.splitshare;

import static com.example.splitshare.R.*;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.splitshare.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //bottom navbar implementation
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView);
                if (item.getItemId() == R.id.homePageFragment) {
                    navController.navigate(R.id.action_global_homePageFragment);
                    return true;
                } else if (item.getItemId() == R.id.groupFragment) {
                    navController.navigate(R.id.action_global_groupFragment);
                    return true;

                } else if (item.getItemId() == R.id.profileFragment) {
                    navController.navigate(R.id.action_global_profilePageFragment);
                    return true;
                } else if (item.getItemId() == R.id.activitiesPageFragment) {
                    navController.navigate(R.id.action_global_activitiesPageFragment);
                    return true;
                } else {
                    Snackbar.make(binding.getRoot(), "Something wasn't right", Snackbar.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

        //this is to ensure that when clicked on the same fragment, this doesn't do anything
        binding.bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homePageFragment) {
                } else if (item.getItemId() == R.id.groupFragment) {

                } else if (item.getItemId() == R.id.profileFragment) {
                } else if (item.getItemId() == R.id.activitiesPageFragment) {
                } else {
                }
            }
        });
    }

    public void hideBottomNavBar() {
        binding.bottomNavigationView.setVisibility(View.GONE);
    }

    public void showBottomNavBar() {
        binding.bottomNavigationView.setVisibility(View.VISIBLE);

    }


}