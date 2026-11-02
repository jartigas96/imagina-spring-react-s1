package com.imagina.eventhub.domain;

import java.math.BigDecimal;

public class Order {

    private final String id;
    private final String customerId;
    private final String eventId;
    private final String ticketTypeId;
    private final int quantity;
    private final BigDecimal total;
    private final String createdAt;
    private final String label;
    private OrderStatus status;

    public Order(String id, String customerId, String eventId, String ticketTypeId, int quantity,
                 BigDecimal total, String createdAt, String label) {
        this.id = id;
        this.customerId = customerId;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.quantity = quantity;
        this.total = total;
        this.createdAt = createdAt;
        this.label = label;
        this.status = OrderStatus.PAID;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getEventId() { return eventId; }
    public String getTicketTypeId() { return ticketTypeId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getTotal() { return total; }
    public String getCreatedAt() { return createdAt; }
    public String getLabel() { return label; }
    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) { this.status = status; }
}
