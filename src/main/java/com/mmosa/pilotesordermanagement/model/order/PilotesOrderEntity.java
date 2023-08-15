package com.mmosa.pilotesordermanagement.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PilotesOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderNumber = UUID.randomUUID().toString();

    @Column(nullable = false)
    //@NotBlank(message = "Delivery address is mandatory")
    private String deliveryAddress;

    @Column(nullable = false)
    //@Min(value = 5, message = "Minimum value is 5")
    //@Max(value = 15, message = "Maximum value is 15")
    private int numberOfPilotes;

    private double orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    //@NotNull(message = "Client is mandatory")
    private ClientEntity client;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
