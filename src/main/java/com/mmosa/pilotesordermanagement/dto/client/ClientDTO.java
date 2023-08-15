package com.mmosa.pilotesordermanagement.dto.client;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Client")
public class ClientDTO {
    @Hidden
    private Long id;

    @Schema(description = "First name", example = "John")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Schema(description = "Last name", example = "Doe")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Invalid phone number format")
    @Schema(description = "Telephone number", example = "+1234567890")
    @NotBlank(message = "Telephone number is mandatory")
    private String telephoneNumber;

    @Hidden
    private String createdAt;
}

