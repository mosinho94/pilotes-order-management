package com.mmosa.pilotesordermanagement.repository;

import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)

class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindByFirstNameContainingIgnoreCase() {
        ClientEntity client1 = new ClientEntity();
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setTelephoneNumber("123-456-7890");

        ClientEntity client2 = new ClientEntity();
        client2.setFirstName("John");
        client2.setLastName("Smith");
        client2.setTelephoneNumber("987-654-3210");

        clientRepository.save(client1);
        clientRepository.save(client2);

        List<ClientEntity> foundClients = clientRepository.findByFirstNameContainingIgnoreCase("john");

        assertEquals(2, foundClients.size());
    }

    @Test
    void testFindClientByTelephoneNumber() {
        ClientEntity client = new ClientEntity();
        client.setFirstName("Jane");
        client.setLastName("Doe");
        client.setTelephoneNumber("123-456-7890");

        clientRepository.save(client);

        List<ClientEntity> foundClients = clientRepository.findClientByTelephoneNumber("123-456-7890");

        assertEquals(1, foundClients.size());
    }
}