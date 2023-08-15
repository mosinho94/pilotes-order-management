package com.mmosa.pilotesordermanagement.service.client;

import com.mmosa.pilotesordermanagement.converter.client.ClientConverter;
import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.exception.client.ClientNotFoundException;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import com.mmosa.pilotesordermanagement.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientConverter clientConverter;

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Override
    public ClientDTO saveClient(ClientDTO client) {
        ClientEntity clientEntity = clientConverter.toEntity(client);
        ClientEntity saveClient = clientRepository.save(clientEntity);
        return clientConverter.toDto(saveClient);
    }

    @Override
    public List<ClientDTO> findClientByFirstName(String firstName) {
        List<ClientEntity> clientEntities = clientRepository.findByFirstNameContainingIgnoreCase(firstName);
        return clientEntities.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> findClientByLastName(String lastName) {
        List<ClientEntity> clientEntities = clientRepository.findByLastNameContainingIgnoreCase(lastName);
        return clientEntities.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> findClientByTelephoneNumber(String telephoneNumber) {
        List<ClientEntity> clientEntities = clientRepository.findClientByTelephoneNumber(telephoneNumber);
        return clientEntities.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return clientEntities.stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO updatedClientDTO) {
        ClientEntity existingClient = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        existingClient.setFirstName(updatedClientDTO.getFirstName());
        existingClient.setLastName(updatedClientDTO.getLastName());
        existingClient.setTelephoneNumber(updatedClientDTO.getTelephoneNumber());
        ClientEntity savedClient = clientRepository.save(existingClient);
        return clientConverter.toDto(savedClient);
    }
}
