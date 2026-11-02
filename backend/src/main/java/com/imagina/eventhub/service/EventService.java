package com.imagina.eventhub.service;

import com.imagina.eventhub.domain.Event;
import com.imagina.eventhub.domain.TicketType;
import com.imagina.eventhub.domain.Venue;
import com.imagina.eventhub.store.InMemoryStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final InMemoryStore store;

    public EventService(InMemoryStore store) {
        this.store = store;
    }

    public List<Event> findAll(String city) {
        List<Event> result = new ArrayList<Event>();
        for (Event event : store.events()) {
            if (city == null || city.equalsIgnoreCase(store.venue(event.getVenueId()).getCity())) {
                result.add(event);
            }
        }
        return result;
    }

    public Event findById(String id) {
        return store.event(id);
    }

    public Venue venueOf(Event event) {
        return store.venue(event.getVenueId());
    }

    public TicketType ticketType(String eventId, String ticketTypeId) {
        Event event = store.event(eventId);
        if (event == null) {
            throw new IllegalArgumentException("Evento no encontrado: " + eventId);
        }
        for (TicketType type : event.getTicketTypes()) {
            if (type.getId().equals(ticketTypeId)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de entrada " + ticketTypeId + " no existe en evento " + eventId);
    }
}
