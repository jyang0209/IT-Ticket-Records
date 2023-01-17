package com.example.ITTicketRecords.repository;

import com.example.ITTicketRecords.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.status = :currentStatus")
    Page<Ticket> findByStatusOrderByRequestedTime(@Param("currentStatus") String currentStatus, Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE " +
            "t.requester LIKE CONCAT('%', :query, '%') " +
            "Or t.requester LIKE CONCAT('%', :query, '%') " +
            "Or t.department LIKE CONCAT('%', :query, '%') " +
            "Or t.briefDescription LIKE CONCAT('%', :query, '%') " +
            "Or t.detailedDescription LIKE CONCAT('%', :query, '%') " +
            "Or t.requestedTime LIKE CONCAT('%', :query, '%') " +
            "Or t.resolution LIKE CONCAT('%', :query, '%') " +
            "Or t.ITmember LIKE CONCAT('%', :query, '%')")
    Page<Ticket> searchTickets(@Param("query") String query, Pageable pageable);
}
