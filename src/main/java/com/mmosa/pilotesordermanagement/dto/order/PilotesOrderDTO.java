package com.mmosa.pilotesordermanagement.dto.order;

import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.validator.pilotescount.order.ValidPilotCount;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(name = "Pilotes Order")
public class PilotesOrderDTO {
    @Hidden
    private Long id;

    @Schema(description = "Delivery address", example = "123 Main St, City")
    @NotBlank(message = "Delivery address is mandatory")
    private String deliveryAddress;

    @Schema(description = "Number of pilots in the order. Must be 5, 10, or 15.", example = "10")
    @ValidPilotCount
    @NotNull(message = "Number of pilots is mandatory")
    private int numberOfPilotes;

    @Hidden
    private ClientDTO client;
    @Hidden
    private double orderTotal;
    @Hidden
    private String createAt;
}

