package com.imagina.eventhub.graphql;

import com.imagina.eventhub.domain.Customer;
import com.imagina.eventhub.domain.Event;
import com.imagina.eventhub.domain.Order;
import com.imagina.eventhub.domain.OrderStatus;
import com.imagina.eventhub.domain.TicketType;
import com.imagina.eventhub.security.CurrentUser;
import com.imagina.eventhub.service.EventService;
import com.imagina.eventhub.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderGraphqlController {

    private final OrderService orderService;
    private final EventService eventService;

    public OrderGraphqlController(OrderService orderService, EventService eventService) {
        this.orderService = orderService;
        this.eventService = eventService;
    }

    @QueryMapping
    public List<Order> myOrders() {
        return orderService.findByCustomer(CurrentUser.customerId());
    }

    /** Backoffice: listado completo de pedidos. */
    @QueryMapping
    public List<Order> orders(@Argument OrderStatus status) {
        return orderService.findAll(status);
    }

    @MutationMapping
    public Order createOrder(@Argument CreateOrderInput input) {
        return orderService.create(CurrentUser.customerId(), input.getEventId(),
                input.getTicketTypeId(), input.getQuantity());
    }

    @MutationMapping
    public Order cancelOrder(@Argument String id) {
        CurrentUser.customerId(); // exige usuario autenticado
        return orderService.cancel(id);
    }

    /** Backoffice: solo accesible desde la pantalla /admin del frontend. */
    @MutationMapping
    public Order refundOrder(@Argument String id, @Argument String reason) {
        return orderService.refund(id, reason);
    }

    @SchemaMapping(typeName = "Order", field = "event")
    public Event event(Order order) {
        return eventService.findById(order.getEventId());
    }

    @SchemaMapping(typeName = "Order", field = "ticketType")
    public TicketType ticketType(Order order) {
        return eventService.ticketType(order.getEventId(), order.getTicketTypeId());
    }

    @SchemaMapping(typeName = "Order", field = "total")
    public String total(Order order) {
        return order.getTotal().toPlainString();
    }

    @SchemaMapping(typeName = "Order", field = "customer")
    public Customer customer(Order order) {
        return orderService.customerOf(order);
    }
}
