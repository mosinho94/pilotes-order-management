package com.mmosa.pilotesordermanagement.converter.order;

import com.mmosa.pilotesordermanagement.converter.client.ClientConverter;
import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PilotesOrderConverterImpl implements PilotesOrderConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ClientConverter clientConverter;


    @Override
    public PilotesOrderDTO toDto(PilotesOrderEntity entity) {
        PilotesOrderDTO dto = new PilotesOrderDTO();
        dto.setId(entity.getId());
        dto.setDeliveryAddress(entity.getDeliveryAddress());
        dto.setNumberOfPilotes(entity.getNumberOfPilotes());
        dto.setOrderTotal(entity.getOrderTotal());
        dto.setCreateAt(entity.getCreatedAt().format(DATE_TIME_FORMATTER));
        dto.setClient(clientConverter.toDto(entity.getClient()));
        return dto;
    }

    @Override
    public PilotesOrderEntity toEntity(PilotesOrderDTO dto) {
        PilotesOrderEntity entity = new PilotesOrderEntity();
        entity.setId(dto.getId());
        entity.setDeliveryAddress(dto.getDeliveryAddress());
        entity.setNumberOfPilotes(dto.getNumberOfPilotes());
        entity.setOrderTotal(dto.getOrderTotal());
        if (dto.getClient() != null) {
            entity.setClient(clientConverter.toEntity(dto.getClient()));
        }
        return entity;
    }

    @Override
    public List<PilotesOrderDTO> toDtoList(List<PilotesOrderEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PilotesOrderEntity> toEntityList(List<PilotesOrderDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}

