package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parkfinder.R;
import com.example.parkfinder.ParkingDataManager;
import com.example.parkfinder.ParkingSpot;
import com.example.parkfinder.activities.ParkingDetailsActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.util.GeoPoint;

public class MapHomeFragment extends Fragment {

    private MapView map = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Configuration.getInstance().load(
                requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext())
        );
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        View view = inflater.inflate(R.layout.fragment_map_home, container, false);

        // Filter Button
        ImageButton btnFilter = view.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            FiltersBottomSheet bottomSheet = new FiltersBottomSheet();
            bottomSheet.show(getParentFragmentManager(), "FiltersBottomSheet");
        });

        // Map Setup
        map = view.findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        // My Location
        MyLocationNewOverlay myLocationOverlay =
                new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);

        // Recenter Button
        view.findViewById(R.id.fabRecenter).setOnClickListener(v -> {
            if (myLocationOverlay.getMyLocation() != null) {
                map.getController().animateTo(
                        myLocationOverlay.getMyLocation(),
                        15.0,
                        1000L
                );
            }
        });

        // Generate Parking Pins After GPS Fix
        myLocationOverlay.runOnFirstFix(() -> {
            requireActivity().runOnUiThread(() -> {

                if (myLocationOverlay.getMyLocation() == null) return;

                double currentLat = myLocationOverlay.getMyLocation().getLatitude();
                double currentLon = myLocationOverlay.getMyLocation().getLongitude();

                map.getController().setCenter(myLocationOverlay.getMyLocation());
                map.getController().setZoom(14.0);

                ParkingDataManager.getInstance()
                        .generateDynamicSpots(currentLat, currentLon);

                for (ParkingSpot spot :
                        ParkingDataManager.getInstance().sharedSpots) {

                    Marker marker = new Marker(map);
                    marker.setPosition(new GeoPoint(spot.lat, spot.lon));
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    marker.setTitle(spot.name);
                    marker.setSnippet("₹" + spot.price + "/hr • "
                            + spot.availability + " slots");

                    marker.setOnMarkerClickListener((m, mapView) -> {

                        android.widget.TextView tvTitle =
                                view.findViewById(R.id.tvNearbyTitle);
                        android.widget.TextView tvSpots =
                                view.findViewById(R.id.tvNearbySpots);
                        androidx.cardview.widget.CardView cardNearbyParking =
                                view.findViewById(R.id.cardNearbyParking);

                        tvTitle.setText(spot.name);
                        tvSpots.setText(spot.availability + " SPOTS");

                        mapView.getController().animateTo(
                                new GeoPoint(spot.lat - 0.005, spot.lon)
                        );

                        cardNearbyParking.setOnClickListener(v -> {
                            Intent intent = new Intent(
                                    getActivity(),
                                    ParkingDetailsActivity.class
                            );

                            intent.putExtra("SPOT_NAME", spot.name);
                            intent.putExtra("SPOT_PRICE", spot.price);
                            intent.putExtra("SPOT_DISTANCE", spot.distance);
                            intent.putExtra("SPOT_AVAIL", spot.availability);

                            startActivity(intent);
                        });

                        m.showInfoWindow();
                        return true;
                    });

                    map.getOverlays().add(marker);
                }

                map.invalidate();
            });
        });

        // ✅ Updated Search Bar Logic
        android.widget.EditText etSearchLocation =
                view.findViewById(R.id.etSearchLocation);

        etSearchLocation.addTextChangedListener(new android.text.TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(android.text.Editable s) {

                String query = s.toString().toLowerCase().trim();

                // Start searching after 3 characters
                if (query.length() >= 3) {

                    for (ParkingSpot spot :
                            ParkingDataManager.getInstance().sharedSpots) {

                        if (spot.name.toLowerCase().contains(query)) {

                            // Jump camera smoothly to matching spot
                            map.getController().animateTo(
                                    new org.osmdroid.util.GeoPoint(spot.lat, spot.lon),
                                    16.0,
                                    800L
                            );

                            break; // Stop after first match
                        }
                    }
                }
            }
        });

        return view;
    }

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