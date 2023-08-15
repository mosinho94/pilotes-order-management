package com.mmosa.pilotesordermanagement.converter.order;

import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;

import java.util.List;

public interface PilotesOrderConverter {
    PilotesOrderDTO toDto(PilotesOrderEntity pilotesOrderEntity);
    PilotesOrderEntity toEntity(PilotesOrderDTO pilotesOrderDTO);

    List<PilotesOrderDTO> toDtoList(List<PilotesOrderEntity> entities);

    List<PilotesOrderEntity> toEntityList(List<PilotesOrderDTO> dtos);
}
