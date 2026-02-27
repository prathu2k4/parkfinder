package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class AddSpaceStep3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_space_step3);

        Toolbar toolbar = findViewById(R.id.appBarLayout).findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        MaterialButton btnSubmitPublish = findViewById(R.id.btnSubmitPublish);
        btnSubmitPublish.setOnClickListener(v -> {
            // Display the success message mimicking the Figma popup
            Toast.makeText(this, "Parking space added successfully!", Toast.LENGTH_LONG).show();

            // Route back to the main dashboard and clear the wizard from the back stack
            Intent intent = new Intent(AddSpaceStep3Activity.this, OwnerDashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}