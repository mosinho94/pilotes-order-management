package com.mmosa.pilotesordermanagement.repository;

import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import com.mmosa.pilotesordermanagement.repository.order.PilotesOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)


class OrderRepositoryTest {

    @Autowired
    private PilotesOrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSearchOrdersByClientData() {
        ClientEntity client = new ClientEntity();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setTelephoneNumber("123-456-7890");

        clientRepository.save(client);

        PilotesOrderEntity order1 = new PilotesOrderEntity();
        order1.setDeliveryAddress("123 Main St");
        order1.setNumberOfPilotes(10);
        order1.setClient(client);

        PilotesOrderEntity order2 = new PilotesOrderEntity();
        order2.setDeliveryAddress("456 Elm St");
        order2.setNumberOfPilotes(15);
        order2.setClient(client);

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<PilotesOrderEntity> foundOrders = orderRepository.searchOrdersByClientData("john");

        assertEquals(2, foundOrders.size());
    }

    @Test
    void testSaveOrder() {
        ClientEntity client = new ClientEntity();
        client.setFirstName("Alice");
        client.setLastName("Johnson");
        client.setTelephoneNumber("555-123-4567");

        clientRepository.save(client);

        PilotesOrderEntity order = new PilotesOrderEntity();
        order.setDeliveryAddress("789 Oak St");
        order.setNumberOfPilotes(8);
        order.setClient(client);

        PilotesOrderEntity savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderNumber());
        assertEquals("789 Oak St", savedOrder.getDeliveryAddress());
        assertEquals(8, savedOrder.getNumberOfPilotes());
        assertEquals(client, savedOrder.getClient());
    }
}
