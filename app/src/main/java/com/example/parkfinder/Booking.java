package com.example.parkfinder;

public class Booking {
    // Notice the 'public' keyword so your fragments can access these!
    public String name, status, address, dateTime, price, actionText;

    public Booking(String name, String status, String address, String dateTime, String price, String actionText) {
        this.name = name;
        this.status = status;
        this.address = address;
        this.dateTime = dateTime;
        this.price = price;
        this.actionText = actionText;
    }
}