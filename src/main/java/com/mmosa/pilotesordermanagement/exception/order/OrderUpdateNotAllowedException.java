package com.mmosa.pilotesordermanagement.exception.order;

public class OrderUpdateNotAllowedException extends RuntimeException {
    public OrderUpdateNotAllowedException(Long orderId) {
        super("Update not allowed for order with ID " + orderId + " after 5 minutes");
    }
}

