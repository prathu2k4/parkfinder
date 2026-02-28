package com.example.parkfinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkfinder.R;
import com.example.parkfinder.activities.AddVehicleActivity;
import com.example.parkfinder.activities.ModeSelectionActivity;
import com.example.parkfinder.activities.PaymentActivity;
import com.example.parkfinder.activities.WelcomeActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView menuSwitchMode = view.findViewById(R.id.menuSwitchMode);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        // Switch Mode
        menuSwitchMode.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ModeSelectionActivity.class);
            startActivity(intent);
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // ✅ My Vehicles
        view.findViewById(R.id.menuMyVehicles).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddVehicleActivity.class);
            startActivity(intent);
        });

        // ✅ Payment Methods
        view.findViewById(R.id.menuPaymentMethods).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            startActivity(intent);
        });

        // ✅ Edit Profile (Placeholder)
        view.findViewById(R.id.menuEditProfile).setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Edit Profile coming soon",
                        Toast.LENGTH_SHORT).show()
        );

        // ✅ Help & Support (Placeholder)
        view.findViewById(R.id.menuHelpSupport).setOnClickListener(v ->
                Toast.makeText(getContext(),
                        "Opening Support Chat...",
                        Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}