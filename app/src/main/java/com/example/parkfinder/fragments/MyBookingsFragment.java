package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkfinder.Booking;
import com.example.parkfinder.BookingAdapter;
import com.example.parkfinder.R;
import java.util.ArrayList;
import java.util.List;

public class MyBookingsFragment extends Fragment {

    // 1. Declared at the Class Level so the click listeners can access them!
    private BookingAdapter adapter;
    private List<Booking> activeBookings;
    private List<Booking> pastBookings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        RecyclerView rvBookings = view.findViewById(R.id.rvBookings);
        rvBookings.setLayoutManager(new LinearLayoutManager(getContext()));

        // 2. Setup the Dummy Data
        activeBookings = new ArrayList<>();
        activeBookings.add(new Booking("Metro Station Parking", "Active", "MG Road, Platform 1", "Today, 10:00 AM - 2:00 PM", "120", "Track Booking"));

        pastBookings = new ArrayList<>();
        pastBookings.add(new Booking("City Center Mall", "Completed", "Downtown Ave", "12 Oct, 6:00 PM - 9:00 PM", "90", "☆ Rate & Review"));
        pastBookings.add(new Booking("Airport Terminal 2", "Completed", "Airport Road", "05 Oct, 8:00 AM - 8:00 PM", "350", "☆ Rate & Review"));

        // 3. Initialize the Adapter and attach it to the RecyclerView
        adapter = new BookingAdapter(activeBookings);
        rvBookings.setAdapter(adapter);

        // 4. Tab Click Listeners (Now they can find 'adapter'!)
        TextView tabActive = view.findViewById(R.id.tabActive);
        TextView tabPast = view.findViewById(R.id.tabPast);

        if (tabActive != null && tabPast != null) {
            tabActive.setOnClickListener(v -> {
                adapter.updateData(activeBookings);
                tabActive.setTextColor(getResources().getColor(R.color.teal_main));
                tabPast.setTextColor(getResources().getColor(R.color.light_grey));
            });

            tabPast.setOnClickListener(v -> {
                adapter.updateData(pastBookings);
                tabPast.setTextColor(getResources().getColor(R.color.teal_main));
                tabActive.setTextColor(getResources().getColor(R.color.light_grey));
            });
        }

        return view;
    }
}