package com.example.parkfinder.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SelectTimeSlotActivity extends AppCompatActivity {

    private TextInputEditText etStartDate, etStartTime, etEndDate, etEndTime;
    private TextView tvCalculatedTotal;
    private Button btnContinueToSummary;

    private Calendar startCal = Calendar.getInstance();
    private Calendar endCal = Calendar.getInstance();
    private final double HOURLY_RATE = 30.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_slot);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        etStartDate = findViewById(R.id.etStartDate);
        etStartTime = findViewById(R.id.etStartTime);
        // Assuming you duplicated the XML ids for end date/time as suggested earlier
        etEndDate = findViewById(R.id.etEndDate);
        etEndTime = findViewById(R.id.etEndTime);
        tvCalculatedTotal = findViewById(R.id.tvCalculatedTotal);
        btnContinueToSummary = findViewById(R.id.btnContinueToSummary);

        // Disable button initially
        btnContinueToSummary.setEnabled(false);

        // Setup Click Listeners for Pickers
        etStartDate.setOnClickListener(v -> showDatePicker(startCal, etStartDate));
        etStartTime.setOnClickListener(v -> showTimePicker(startCal, etStartTime));
        etEndDate.setOnClickListener(v -> showDatePicker(endCal, etEndDate));
        etEndTime.setOnClickListener(v -> showTimePicker(endCal, etEndTime));

        btnContinueToSummary.setOnClickListener(v -> {
            Intent intent = new Intent(SelectTimeSlotActivity.this, BookingSummaryActivity.class);
            startActivity(intent);
        });
    }

    private void showDatePicker(Calendar calendar, TextInputEditText editText) {
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            editText.setText(sdf.format(calendar.getTime()));
            calculatePrice();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker(Calendar calendar, TextInputEditText editText) {
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            editText.setText(sdf.format(calendar.getTime()));
            calculatePrice();
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void calculatePrice() {
        // Ensure all fields are filled before calculating
        if (etStartDate.getText().toString().isEmpty() || etStartTime.getText().toString().isEmpty() ||
                etEndDate.getText().toString().isEmpty() || etEndTime.getText().toString().isEmpty()) {
            return;
        }

        long diffInMillis = endCal.getTimeInMillis() - startCal.getTimeInMillis();

        if (diffInMillis <= 0) {
            Toast.makeText(this, "End time must be after start time", Toast.LENGTH_SHORT).show();
            btnContinueToSummary.setEnabled(false);
            tvCalculatedTotal.setText("₹0");
        } else {
            // Convert milliseconds to hours
            double hours = diffInMillis / (1000.0 * 60 * 60);
            int totalCost = (int) Math.ceil(hours * HOURLY_RATE);

            tvCalculatedTotal.setText("₹" + totalCost);
            btnContinueToSummary.setEnabled(true);
        }
    }
}