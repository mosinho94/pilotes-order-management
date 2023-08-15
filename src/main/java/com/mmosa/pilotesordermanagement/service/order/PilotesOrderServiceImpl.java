package com.mmosa.pilotesordermanagement.service.order;

import com.mmosa.pilotesordermanagement.converter.client.ClientConverter;
import com.mmosa.pilotesordermanagement.converter.order.PilotesOrderConverter;
import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.exception.client.ClientNotFoundException;
import com.mmosa.pilotesordermanagement.exception.order.OrderNotFoundException;
import com.mmosa.pilotesordermanagement.exception.order.OrderUpdateNotAllowedException;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import com.mmosa.pilotesordermanagement.repository.order.PilotesOrderRepository;
import com.mmosa.pilotesordermanagement.service.client.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PilotesOrderServiceImpl implements PilotesOrderService {
    private final PilotesOrderRepository orderRepository;
    private final ClientServiceImpl clientService;
    private final ClientConverter clientConverter;
    private final ClientRepository clientRepository;
    private final PilotesOrderConverter orderConverter;

    @Autowired
    public PilotesOrderServiceImpl(
            PilotesOrderRepository orderRepository,
            ClientServiceImpl clientService,
            ClientConverter clientConverter,
            ClientRepository clientRepository,
            PilotesOrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.clientConverter = clientConverter;
        this.clientRepository = clientRepository;
        this.orderConverter = orderConverter;
    }

    @Override
    public PilotesOrderDTO createOrder(Long clientId, PilotesOrderDTO order) {
        ClientEntity clientEntity = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        PilotesOrderEntity pilotesOrder = orderConverter.toEntity(order);

        pilotesOrder.setClient(clientEntity);
        pilotesOrder.setOrderTotal(1.33 * order.getNumberOfPilotes());
        PilotesOrderEntity savedOrder = orderRepository.save(pilotesOrder);
        return orderConverter.toDto(savedOrder);
    }

    @Override
    public PilotesOrderDTO updateOrder(Long orderId, PilotesOrderDTO updatedOrderDTO) {
        PilotesOrderEntity existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        LocalDateTime currentTime = LocalDateTime.now();
        if (!isWithin5Minutes(existingOrder.getCreatedAt(), currentTime)) {
            throw new OrderUpdateNotAllowedException(orderId);
        }

        existingOrder.setNumberOfPilotes(updatedOrderDTO.getNumberOfPilotes());
        existingOrder.setDeliveryAddress(updatedOrderDTO.getDeliveryAddress());

        PilotesOrderEntity updatedOrder = orderRepository.save(existingOrder);

        return orderConverter.toDto(updatedOrder);
    }

    @Override
    public List<PilotesOrderDTO> searchOrders(String searchTerm) {
        List<PilotesOrderEntity> orderEntities = orderRepository.searchOrdersByClientData(searchTerm);
        return orderEntities.stream()
                .map(order -> orderConverter.toDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) {
        PilotesOrderEntity pilotesOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.delete(pilotesOrder);

    }

    private boolean isWithin5Minutes(LocalDateTime creationTime, LocalDateTime currentTime) {
        return creationTime.plusMinutes(5).isAfter(currentTime);
    }

}
