package com.mmosa.pilotesordermanagement.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mmosa.pilotesordermanagement.model.client.ClientEntity;

import java.util.List;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findByFirstNameContainingIgnoreCase(String firstName);

    List<ClientEntity> findByLastNameContainingIgnoreCase(String lastName);

    List<ClientEntity> findClientByTelephoneNumber(String telephoneNumber);
}

