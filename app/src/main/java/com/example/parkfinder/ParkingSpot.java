package com.example.parkfinder;

public class ParkingSpot {
    public String name, distance, timings, price, availability;
    public double lat, lon; // Added coordinates

    public ParkingSpot(String name, String distance, String timings, String price, String availability, double lat, double lon) {
        this.name = name;
        this.distance = distance;
        this.timings = timings;
        this.price = price;
        this.availability = availability;
        this.lat = lat;
        this.lon = lon;
    }
}