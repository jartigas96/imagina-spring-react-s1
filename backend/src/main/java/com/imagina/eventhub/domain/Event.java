package com.imagina.eventhub.domain;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private final String id;
    private final String title;
    private final String date;
    private final String venueId;
    private final List<TicketType> ticketTypes = new ArrayList<TicketType>();

    public Event(String id, String title, String date, String venueId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.venueId = venueId;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getVenueId() { return venueId; }
    public List<TicketType> getTicketTypes() { return ticketTypes; }
}
