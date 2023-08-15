package com.mmosa.pilotesordermanagement.service.order;

import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;

import java.util.List;

public interface PilotesOrderService {

    PilotesOrderDTO createOrder(Long clientId, PilotesOrderDTO order);

    PilotesOrderDTO updateOrder(Long orderId, PilotesOrderDTO updatedOrderDTO);

    List<PilotesOrderDTO> searchOrders(String searchTerm);

    void deleteOrder(Long orderId);
}



