package com.imagina.eventhub.domain;

import java.math.BigDecimal;

public class TicketType {

    private final String id;
    private final String name;
    private final BigDecimal price;
    private int available;

    public TicketType(String id, String name, BigDecimal price, int available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getAvailable() { return available; }

    public void setAvailable(int available) { this.available = available; }
}
