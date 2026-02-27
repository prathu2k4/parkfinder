package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.parkfinder.R;

// OSMDroid imports
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;

public class MapHomeFragment extends Fragment {

    private MapView map = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // IMPORTANT: Initialize the OSMDroid configuration before inflating the layout.
        // This prevents the map from failing to load tile imagery.
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        View view = inflater.inflate(R.layout.fragment_map_home, container, false);

        // 1. Setup the Filter Button
        ImageButton btnFilter = view.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            FiltersBottomSheet bottomSheet = new FiltersBottomSheet();
            bottomSheet.show(getParentFragmentManager(), "FiltersBottomSheet");
        });

        // 2. Setup the OpenStreetMap View
        map = view.findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK); // Standard OpenStreetMap style
        map.setMultiTouchControls(true); // Enables pinch-to-zoom
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER); // Hides default + / - buttons

        // 3. Center the map camera (e.g., Bengaluru coordinates)
        double startLat = 12.9716;
        double startLon = 77.5946;
        GeoPoint startPoint = new GeoPoint(startLat, startLon);

        map.getController().setZoom(15.0); // 15 is a good street-level zoom
        map.getController().setCenter(startPoint);

        return view;
    }

    // Lifecycle methods are required for OSMDroid to pause rendering when the app is in the background
    @Override
    public void onResume() {
        super.onResume();
        if (map != null) map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (map != null) map.onPause();
    }
}