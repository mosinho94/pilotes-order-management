package com.mmosa.pilotesordermanagement.converter.client;

import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ClientConverterImpl implements ClientConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ClientDTO toDto(ClientEntity clientEntity) {
        ClientDTO target = new ClientDTO();
        target.setId(clientEntity.getId());
        target.setFirstName(clientEntity.getFirstName());
        target.setLastName(clientEntity.getLastName());
        target.setTelephoneNumber(clientEntity.getTelephoneNumber());
        target.setCreatedAt(clientEntity.getCreatedAt() != null ? clientEntity.getCreatedAt().format(DATE_TIME_FORMATTER) : null);

        return target;
    }

    @Override
    public ClientEntity toEntity(ClientDTO clientDTO) {
        ClientEntity entity = new ClientEntity();
        if (clientDTO.getId() != null) {
            entity.setId(clientDTO.getId());
        }
        entity.setFirstName(clientDTO.getFirstName());
        entity.setLastName(clientDTO.getLastName());
        entity.setTelephoneNumber(clientDTO.getTelephoneNumber());
        entity.setCreatedAt(clientDTO.getCreatedAt() != null ? LocalDateTime.parse(clientDTO.getCreatedAt(), DATE_TIME_FORMATTER) : null);

        return entity;
    }
}
