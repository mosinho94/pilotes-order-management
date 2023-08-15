package com.mmosa.pilotesordermanagement.service.order;

import com.mmosa.pilotesordermanagement.converter.order.PilotesOrderConverter;
import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import com.mmosa.pilotesordermanagement.repository.order.PilotesOrderRepository;
import com.mmosa.pilotesordermanagement.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private PilotesOrderServiceImpl orderService;
    @Mock
    private PilotesOrderRepository orderRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private PilotesOrderConverter orderConverter;

    @Test
    void testCreateOrder() {
        Long clientId = 1L;
        PilotesOrderDTO orderDTO = new PilotesOrderDTO();
        ClientEntity clientEntity = new ClientEntity();
        PilotesOrderEntity savedOrderEntity = new PilotesOrderEntity();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(orderRepository.save(any())).thenReturn(savedOrderEntity);
        when(orderConverter.toEntity(orderDTO)).thenReturn(new PilotesOrderEntity());
        when(orderConverter.toDto(savedOrderEntity)).thenReturn(new PilotesOrderDTO());

        PilotesOrderDTO pilotesOrderDTO = orderService.createOrder(clientId, orderDTO);


        assertNotNull(pilotesOrderDTO);
        assertEquals(1.33 * orderDTO.getNumberOfPilotes(), pilotesOrderDTO.getOrderTotal());
    }


    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        PilotesOrderDTO updatedOrderDTO = new PilotesOrderDTO();
        PilotesOrderEntity existingOrder = new PilotesOrderEntity();
        existingOrder.setNumberOfPilotes(15);
        existingOrder.setDeliveryAddress("123 Main St");
        existingOrder.setCreatedAt(LocalDateTime.now());
        existingOrder.setClient(new ClientEntity());

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any())).thenReturn(existingOrder);
        when(orderConverter.toDto(existingOrder)).thenReturn(new PilotesOrderDTO());

        PilotesOrderDTO updatedOrder = orderService.updateOrder(orderId, updatedOrderDTO);

        assertNotNull(updatedOrder);
    }

    @Test
    void testSearchOrders() {
        String searchTerm = "searchTerm";
        List<PilotesOrderEntity> orders = List.of(new PilotesOrderEntity(), new PilotesOrderEntity());
        when(orderRepository.searchOrdersByClientData(searchTerm)).thenReturn(orders);
        when(orderConverter.toDto(any(PilotesOrderEntity.class))).thenAnswer(invocation -> {
            PilotesOrderEntity orderEntity = invocation.getArgument(0);
            PilotesOrderDTO orderDTO = new PilotesOrderDTO();
            // Popola orderDTO con i dati da orderEntity
            return orderDTO;
        });

        List<PilotesOrderDTO> foundOrders = orderService.searchOrders(searchTerm);

        assertEquals(orders.size(), foundOrders.size());
        // Altre asserzioni possono essere aggiunte in base alle tue necessità
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;
        PilotesOrderEntity orderEntity = new PilotesOrderEntity();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));

        assertDoesNotThrow(() -> orderService.deleteOrder(orderId));
    }
}