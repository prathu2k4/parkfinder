package com.example.parkfinder.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.parkfinder.R;
import com.example.parkfinder.fragments.DashboardHomeFragment;
import com.example.parkfinder.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OwnerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // You would need an activity_owner_main.xml layout similar to activity_main.xml
        // with a FragmentContainerView and a BottomNavigationView specific to the Owner
        setContentView(R.layout.activity_main); // Reusing for placeholder

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DashboardHomeFragment()).commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            // Note: Menu IDs would match your Owner bottom_nav_menu
            if (itemId == R.id.nav_map) {
                selectedFragment = new DashboardHomeFragment();
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