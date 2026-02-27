package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class AddSpaceStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_space_step2);

        Toolbar toolbar = findViewById(R.id.appBarLayout).findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        MaterialButton btnContinueToStep3 = findViewById(R.id.btnContinueToStep3);
        btnContinueToStep3.setOnClickListener(v -> {
            Intent intent = new Intent(AddSpaceStep2Activity.this, AddSpaceStep3Activity.class);
            startActivity(intent);
        });
    }
}