package com.mmosa.pilotesordermanagement.controller.order;

import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.service.order.PilotesOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Order management APIs")
public class PilotesOrderController {

    private PilotesOrderService orderService;

    @Autowired
    public PilotesOrderController(PilotesOrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new order")
    @ResponseStatus(HttpStatus.CREATED)
    public PilotesOrderDTO createOrder(@PathVariable Long clientId, @RequestBody @Valid PilotesOrderDTO orderDTO) {
        return orderService.createOrder(clientId, orderDTO);
    }

    @PutMapping(value = "/{orderId}")
    @Operation(summary = "Update an order by Id")
    @ResponseStatus(HttpStatus.OK)
    public PilotesOrderDTO updateOrder(@PathVariable Long orderId, @RequestBody @Valid PilotesOrderDTO updatedOrderDTO) {
        return orderService.updateOrder(orderId, updatedOrderDTO);
    }

    @DeleteMapping(value = "/{orderId}")
    @Operation(summary = "Delete an order by Id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(
            @PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search orders", security = @SecurityRequirement(name = "basicAuth"))
    public List<PilotesOrderDTO> searchOrders(@RequestParam String searchTerm) {
        return orderService.searchOrders(searchTerm);
    }


}
