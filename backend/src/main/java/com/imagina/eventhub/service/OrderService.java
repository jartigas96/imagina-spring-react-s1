package com.imagina.eventhub.service;

import com.imagina.eventhub.domain.Customer;
import com.imagina.eventhub.domain.Event;
import com.imagina.eventhub.domain.Order;
import com.imagina.eventhub.domain.OrderStatus;
import com.imagina.eventhub.domain.TicketType;
import com.imagina.eventhub.store.InMemoryStore;
import com.imagina.eventhub.util.TicketLabelFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final InMemoryStore store;
    private final EventService eventService;
    private final TicketLabelFormatter labelFormatter;

    public OrderService(InMemoryStore store, EventService eventService, TicketLabelFormatter labelFormatter) {
        this.store = store;
        this.eventService = eventService;
        this.labelFormatter = labelFormatter;
    }

    public Order create(String customerId, String eventId, String ticketTypeId, int quantity) {
        Event event = eventService.findById(eventId);
        TicketType type = eventService.ticketType(eventId, ticketTypeId);
        Customer customer = store.customer(customerId);

        BigDecimal total = type.getPrice().multiply(new BigDecimal(quantity));
        type.setAvailable(type.getAvailable() - quantity);

        String now = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date());
        String label = labelFormatter.format(customer.getName(), event.getTitle());
        Order order = new Order(store.nextOrderId(), customerId, eventId, ticketTypeId, quantity, total, now, label);
        log.debug("Pedido creado {} para {} ({})", order.getId(), customer.getEmail(), customer.getPhone());
        return store.save(order);
    }

    public Order cancel(String orderId) {
        Order order = store.order(orderId);
        if (order.getStatus() != OrderStatus.PAID) {
            throw new IllegalStateException("Solo se pueden cancelar pedidos en estado PAID");
        }
        order.setStatus(OrderStatus.CANCELLED);
        return order;
    }

    public Order refund(String orderId, String reason) {
        Order order = store.order(orderId);
        order.setStatus(OrderStatus.REFUNDED);
        log.info("Reembolso del pedido {}: {}", orderId, reason);
        return order;
    }

    public Order findById(String orderId) {
        return store.order(orderId);
    }

    public List<Order> findByCustomer(String customerId) {
        List<Order> result = new ArrayList<Order>();
        for (Order order : store.orders()) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findAll(OrderStatus status) {
        List<Order> result = new ArrayList<Order>();
        for (Order order : store.orders()) {
            if (status == null || order.getStatus() == status) {
                result.add(order);
            }
        }
        return result;
    }

    public Customer customerOf(Order order) {
        return store.customer(order.getCustomerId());
    }
}
