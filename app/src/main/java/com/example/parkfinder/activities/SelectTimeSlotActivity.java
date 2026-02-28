package com.example.parkfinder.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.parkfinder.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SelectTimeSlotActivity extends AppCompatActivity {

    private TextInputEditText etStartDate, etStartTime, etEndDate, etEndTime;
    private TextView tvCalculatedTotal, tvDuration;
    private Button btnContinueToSummary;
    private final double HOURLY_RATE = 30.0;

    private Calendar startCal = Calendar.getInstance();
    private Calendar endCal = Calendar.getInstance();
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private final SimpleDateFormat timeFormat =
            new SimpleDateFormat("hh:mm a", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_slot);

        setupUI();
        setupQuickSelect();
        setupPickers();
    }

    private void setupUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        etStartDate = findViewById(R.id.etStartDate);
        etStartTime = findViewById(R.id.etStartTime);
        etEndDate = findViewById(R.id.etEndDate);
        etEndTime = findViewById(R.id.etEndTime);
        tvCalculatedTotal = findViewById(R.id.tvCalculatedTotal);
        tvDuration = findViewById(R.id.tvDuration);
        btnContinueToSummary = findViewById(R.id.btnContinueToSummary);

        btnContinueToSummary.setEnabled(false);

        // ✅ UPDATED Continue Button
        btnContinueToSummary.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SelectTimeSlotActivity.this,
                    BookingSummaryActivity.class
            );

            // Pass original spot name from previous screen
            intent.putExtra("SPOT_NAME",
                    getIntent().getStringExtra("SPOT_NAME"));

            intent.putExtra("DURATION",
                    tvDuration.getText().toString());

            intent.putExtra("TOTAL_COST",
                    tvCalculatedTotal.getText().toString());

            startActivity(intent);
        });
    }

    private void setupQuickSelect() {

        int[] ids = {R.id.btn1h, R.id.btn2h, R.id.btn3h,
                R.id.btn4h, R.id.btn6h, R.id.btn8h};

        int[] hoursToAdd = {1, 2, 3, 4, 6, 8};

        for (int i = 0; i < ids.length; i++) {

            final int durationInHours = hoursToAdd[i];

            findViewById(ids[i]).setOnClickListener(v -> {

                String priceStr =
                        getIntent().getStringExtra("SPOT_PRICE");

                if (priceStr == null) return;

                int hourlyRate = Integer.parseInt(priceStr);

                int totalCost = durationInHours * hourlyRate;

                tvDuration.setText(durationInHours + " hours");
                tvCalculatedTotal.setText("₹" + totalCost);

                btnContinueToSummary.setEnabled(true);
            });
        }
    }

    private void setupPickers() {
        etStartDate.setOnClickListener(v -> showDatePicker(startCal, etStartDate));
        etStartTime.setOnClickListener(v -> showTimePicker(startCal, etStartTime));
        etEndDate.setOnClickListener(v -> showDatePicker(endCal, etEndDate));
        etEndTime.setOnClickListener(v -> showTimePicker(endCal, etEndTime));
    }

    private void showDatePicker(Calendar cal, TextInputEditText et) {
        new DatePickerDialog(this, (v, y, m, d) -> {
            cal.set(y, m, d);
            et.setText(dateFormat.format(cal.getTime()));
            calculateFinal();
        }, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker(Calendar cal, TextInputEditText et) {
        new TimePickerDialog(this, (v, h, m) -> {
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            et.setText(timeFormat.format(cal.getTime()));
            calculateFinal();
        }, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false).show();
    }

    private void calculateFinal() {
        if (etStartDate.getText().toString().isEmpty()
                || etEndTime.getText().toString().isEmpty()) return;

        long diff = endCal.getTimeInMillis()
                - startCal.getTimeInMillis();

        if (diff <= 0) {
            tvDuration.setText("Invalid Range");
            tvCalculatedTotal.setText("₹0");
            btnContinueToSummary.setEnabled(false);
        } else {
            double hours = diff / (1000.0 * 60 * 60);
            int total = (int) Math.ceil(hours * HOURLY_RATE);

            tvDuration.setText(
                    String.format(Locale.getDefault(),
                            "%.1f hours", hours));

            tvCalculatedTotal.setText("₹" + total);
            btnContinueToSummary.setEnabled(true);
        }
    }
}