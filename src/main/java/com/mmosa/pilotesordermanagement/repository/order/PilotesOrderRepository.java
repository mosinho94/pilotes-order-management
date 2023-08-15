package com.mmosa.pilotesordermanagement.repository.order;


import com.mmosa.pilotesordermanagement.model.order.PilotesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PilotesOrderRepository extends JpaRepository<PilotesOrderEntity, Long> {

    @Query("SELECT o FROM PilotesOrderEntity o " +
            "WHERE LOWER(o.client.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(o.client.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(o.client.telephoneNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<PilotesOrderEntity> searchOrdersByClientData(@Param("searchTerm") String searchTerm);

}



