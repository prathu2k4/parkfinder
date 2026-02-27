package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class AddSpaceStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_space_step1);

        Toolbar toolbar = findViewById(R.id.appBarLayout).findViewById(R.id.toolbar);
        // Handle potential nulls depending on your exact XML hierarchy
        if(toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        MaterialButton btnContinueToStep2 = findViewById(R.id.btnContinueToStep2);
        btnContinueToStep2.setOnClickListener(v -> {
            Intent intent = new Intent(AddSpaceStep1Activity.this, AddSpaceStep2Activity.class);
            startActivity(intent);
        });
    }
}