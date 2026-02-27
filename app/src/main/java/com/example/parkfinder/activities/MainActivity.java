package com.example.parkfinder.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.parkfinder.R;
import com.example.parkfinder.fragments.ListHomeFragment;
import com.example.parkfinder.fragments.MapHomeFragment;
import com.example.parkfinder.fragments.MyBookingsFragment;
import com.example.parkfinder.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Set Map as the default fragment on load
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MapHomeFragment()).commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_map) {
                selectedFragment = new MapHomeFragment();
            } else if (itemId == R.id.nav_list) {
                selectedFragment = new ListHomeFragment();
            } else if (itemId == R.id.nav_bookings) {
                selectedFragment = new MyBookingsFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }
}