package com.mmosa.pilotesordermanagement.service.client;

import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO saveClient(ClientDTO client);
    List<ClientDTO> findClientByFirstName(String firstName);
    List<ClientDTO> findClientByLastName(String lastName);
    List<ClientDTO> findClientByTelephoneNumber(String telephoneNumber);
    List<ClientDTO> getAllClients();

    ClientDTO updateClient(Long clientId, ClientDTO updatedClientDTO);
}

