package com.mmosa.pilotesordermanagement.model.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "clients")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "First name is mandatory")
    private String firstName;

    //@NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(nullable = false)
    //@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Invalid phone number format")
    private String telephoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
