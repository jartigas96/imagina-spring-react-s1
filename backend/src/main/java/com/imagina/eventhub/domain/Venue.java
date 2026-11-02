package com.imagina.eventhub.domain;

public class Venue {

    private final String id;
    private final String name;
    private final String city;
    private final int capacity;

    public Venue(String id, String name, String city, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getCapacity() { return capacity; }
}
