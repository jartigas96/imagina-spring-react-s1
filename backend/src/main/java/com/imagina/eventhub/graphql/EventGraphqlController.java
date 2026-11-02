package com.imagina.eventhub.graphql;

import com.imagina.eventhub.domain.Event;
import com.imagina.eventhub.domain.TicketType;
import com.imagina.eventhub.domain.Venue;
import com.imagina.eventhub.service.EventService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EventGraphqlController {

    private final EventService eventService;

    public EventGraphqlController(EventService eventService) {
        this.eventService = eventService;
    }

    @QueryMapping
    public List<Event> events(@Argument String city) {
        return eventService.findAll(city);
    }

    @QueryMapping
    public Event event(@Argument String id) {
        return eventService.findById(id);
    }

    @SchemaMapping(typeName = "Event", field = "venue")
    public Venue venue(Event event) {
        return eventService.venueOf(event);
    }

    /** El schema expone price como String decimal ("45.00"), no como número. */
    @SchemaMapping(typeName = "TicketType", field = "price")
    public String price(TicketType ticketType) {
        return ticketType.getPrice().toPlainString();
    }
}
