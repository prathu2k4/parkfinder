package com.example.parkfinder.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class ManageSpaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_space); // Note: Ensure this XML is named correctly in your project

        // Fallback for Toolbar navigation
        Toolbar toolbar = findViewById(R.id.appBarLayout) != null ? findViewById(R.id.appBarLayout).findViewById(R.id.toolbar) : null;
        if(toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        MaterialButton btnEditDetails = findViewById(R.id.btnEditDetails);
        MaterialButton btnDeleteSpace = findViewById(R.id.btnDeleteSpace);

        btnEditDetails.setOnClickListener(v -> {
            Toast.makeText(this, "Edit details clicked", Toast.LENGTH_SHORT).show();
            // Intent to AddSpaceStep2Activity with pre-filled data would go here
        });

        btnDeleteSpace.setOnClickListener(v -> {
            // Trigger the confirmation dialog seen in the prototype
            new AlertDialog.Builder(this)
                    .setTitle("Figma")
                    .setMessage("Are you sure you want to delete this parking space?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        Toast.makeText(this, "Space deleted", Toast.LENGTH_SHORT).show();
                        finish(); // Return to dashboard
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}