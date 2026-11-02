package com.imagina.eventhub.store;

import com.imagina.eventhub.domain.Customer;
import com.imagina.eventhub.domain.Event;
import com.imagina.eventhub.domain.Order;
import com.imagina.eventhub.domain.TicketType;
import com.imagina.eventhub.domain.Venue;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Almacén en memoria con datos semilla. Sustituye a la base de datos para que el
 * proyecto arranque sin infraestructura durante las prácticas.
 */
@Component
public class InMemoryStore {

    private final Map<String, Venue> venues = new LinkedHashMap<String, Venue>();
    private final Map<String, Event> events = new LinkedHashMap<String, Event>();
    private final Map<String, Customer> customers = new LinkedHashMap<String, Customer>();
    private final Map<String, Order> orders = new LinkedHashMap<String, Order>();
    private final AtomicLong orderSequence = new AtomicLong(1000);

    public InMemoryStore() {
        venues.put("v1", new Venue("v1", "Teatre Tívoli", "Barcelona", 1600));
        venues.put("v2", new Venue("v2", "Teatro Real", "Madrid", 1750));

        Event e1 = new Event("e1", "La Traviata", "2026-12-12T20:00", "v2");
        e1.getTicketTypes().add(new TicketType("t1", "Platea", new BigDecimal("95.00"), 120));
        e1.getTicketTypes().add(new TicketType("t2", "Anfiteatro", new BigDecimal("45.00"), 300));
        Event e2 = new Event("e2", "Concierto de Año Nuevo", "2027-01-01T12:00", "v1");
        e2.getTicketTypes().add(new TicketType("t3", "General", new BigDecimal("38.50"), 800));
        events.put(e1.getId(), e1);
        events.put(e2.getId(), e2);

        customers.put("c1", new Customer("c1", "Ana Torres", "ana.torres@example.com", "+34 600 111 222"));
        customers.put("c2", new Customer("c2", "Luis Gómez", "luis.gomez@example.com", "+34 600 333 444"));

        orders.put("o1", new Order("o1", "c1", "e1", "t1", 2, new BigDecimal("190.00"),
                "2026-10-20T10:15", "Entrada: Ana Torres - La Traviata"));
        orders.put("o2", new Order("o2", "c2", "e2", "t3", 4, new BigDecimal("154.00"),
                "2026-10-21T18:40", "Entrada: Luis Gómez - Concierto de Año Nuevo"));
    }

    public Collection<Event> events() { return events.values(); }
    public Event event(String id) { return events.get(id); }
    public Venue venue(String id) { return venues.get(id); }
    public Customer customer(String id) { return customers.get(id); }
    public Order order(String id) { return orders.get(id); }

    public List<Order> orders() { return new ArrayList<Order>(orders.values()); }

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public String nextOrderId() {
        return "o" + orderSequence.incrementAndGet();
    }
}
