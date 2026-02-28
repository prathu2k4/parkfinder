package com.example.parkfinder;

import java.util.ArrayList;
import java.util.List;

public class ParkingDataManager {
    private static ParkingDataManager instance;
    public List<ParkingSpot> sharedSpots = new ArrayList<>();

    private ParkingDataManager() {}

    public static ParkingDataManager getInstance() {
        if (instance == null) {
            instance = new ParkingDataManager();
        }
        return instance;
    }

    public void generateDynamicSpots(double userLat, double userLon) {
        sharedSpots.clear();

        // Roughly: 1 km = 0.009 degrees.
        // 3-5 km = ~0.027 to 0.045 degrees offset.
        // 5-7 km = ~0.045 to 0.063 degrees offset.

        // 5 "Nearby" Spots (3 to 5 km away)
        sharedSpots.add(new ParkingSpot("Central Metro Parking", "3.2 km", "24/7", "30", "15 / 50", userLat + 0.028, userLon + 0.010));
        sharedSpots.add(new ParkingSpot("City Mall Basement", "3.8 km", "8 AM - 11 PM", "50", "12 / 50", userLat - 0.034, userLon + 0.015));
        sharedSpots.add(new ParkingSpot("Street Pay & Park", "4.1 km", "6 AM - 8 PM", "20", "2 / 50", userLat + 0.015, userLon - 0.035));
        sharedSpots.add(new ParkingSpot("Hospital Visitor Lot", "4.5 km", "24/7", "40", "20 / 50", userLat - 0.040, userLon - 0.020));
        sharedSpots.add(new ParkingSpot("Tech Park Alpha", "4.9 km", "6 AM - 10 PM", "60", "45 / 50", userLat + 0.035, userLon + 0.030));

        // 3 "Other" Spots (5 to 7 km away)
        sharedSpots.add(new ParkingSpot("Airport Long Term", "5.5 km", "24/7", "150", "40 / 50", userLat + 0.050, userLon + 0.020));
        sharedSpots.add(new ParkingSpot("Stadium Event Parking", "6.2 km", "Event Days Only", "100", "0 / 50", userLat - 0.010, userLon + 0.055));
        sharedSpots.add(new ParkingSpot("Suburban Train Station", "6.8 km", "24/7", "25", "30 / 50", userLat - 0.060, userLon - 0.025));
    }
}