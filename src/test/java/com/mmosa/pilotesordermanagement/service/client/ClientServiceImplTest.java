package com.mmosa.pilotesordermanagement.service.client;

import com.mmosa.pilotesordermanagement.converter.client.ClientConverter;
import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientConverter clientConverter;

    @Test
    void testSaveClient() {
        ClientDTO clientDTO = new ClientDTO();
        ClientEntity clientEntity = new ClientEntity();

        when(clientConverter.toEntity(clientDTO)).thenReturn(clientEntity);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        when(clientConverter.toDto(clientEntity)).thenReturn(clientDTO);

        ClientDTO savedClient = clientService.saveClient(clientDTO);
        assertNotNull(savedClient);
    }


    @Test
    void testFindClientByFirstName() {
        String firstName = "John";
        List<ClientEntity> clients = new ArrayList<>();

        ClientEntity client1 = new ClientEntity();
        client1.setId(1L);
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setTelephoneNumber("123-456-7890");
        clients.add(client1);

        ClientEntity client2 = new ClientEntity();
        client2.setId(2L);
        client2.setFirstName("John");
        client2.setLastName("Smith");
        client2.setTelephoneNumber("987-654-3210");
        clients.add(client2);

        when(clientRepository.findByFirstNameContainingIgnoreCase(firstName)).thenReturn(clients);

        List<ClientDTO> expectedClientDTOs = clients.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());

        List<ClientDTO> foundClients = clientService.findClientByFirstName(firstName);

        assertEquals(expectedClientDTOs, foundClients);
        verify(clientRepository).findByFirstNameContainingIgnoreCase(firstName);
        verifyNoMoreInteractions(clientRepository);
    }


    @Test
    void testGetAllClients() {
        List<ClientEntity> clients = new ArrayList<>();

        ClientEntity client1 = new ClientEntity();
        client1.setId(1L);
        client1.setFirstName("John");
        client1.setLastName("Doe");
        client1.setTelephoneNumber("123-456-7890");
        clients.add(client1);

        ClientEntity client2 = new ClientEntity();
        client2.setId(2L);
        client2.setFirstName("Jane");
        client2.setLastName("Smith");
        client2.setTelephoneNumber("987-654-3210");
        clients.add(client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDTO> allClients = clientService.getAllClients();
        List<ClientDTO> expectedClientDTOs = clients.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());


        assertEquals(expectedClientDTOs, allClients);
        verify(clientRepository).findAll();
        verifyNoMoreInteractions(clientRepository);
    }

}