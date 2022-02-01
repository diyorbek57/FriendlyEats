package com.ayizor.friendlyeats.models;

public class Restaurant {
    private String name, distance, address, image;
    private double rating;
    int id;

    public Restaurant() {
    }

    public Restaurant(String name, String distance, String address, String image, double rating, int id) {
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.image = image;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
