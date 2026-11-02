package com.imagina.eventhub.rest;

import com.imagina.eventhub.domain.Order;
import com.imagina.eventhub.domain.OrderStatus;
import com.imagina.eventhub.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** API REST heredada que todavía consume la app móvil antigua. */
@RestController
@RequestMapping("/api")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{id}")
    public Order order(@PathVariable String id) {
        return orderService.findById(id);
    }

    @GetMapping("/admin/stats")
    public Map<String, Object> stats() {
        List<Order> all = orderService.findAll(null);
        Map<String, Object> stats = new HashMap<String, Object>();
        stats.put("orders", all.size());
        stats.put("refunded", orderService.findAll(OrderStatus.REFUNDED).size());
        return stats;
    }
}
