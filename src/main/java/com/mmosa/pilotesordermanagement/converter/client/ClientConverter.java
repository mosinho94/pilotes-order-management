package com.mmosa.pilotesordermanagement.converter.client;

import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;

public interface ClientConverter {
    ClientDTO toDto(ClientEntity clientEntity);
    ClientEntity toEntity(ClientDTO clientDTO);
}

