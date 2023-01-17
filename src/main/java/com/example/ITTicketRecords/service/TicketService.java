package com.example.ITTicketRecords.service;

import com.example.ITTicketRecords.entity.Ticket;
import org.springframework.data.domain.Page;

public interface TicketService {

    Ticket getById(Long id);

    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    Page<Ticket> findPaginated(int pageNo, int pageSize);

    Page<Ticket> findPaginatedBasedOnStatus(int pageNo, int pageSize, String currentStatus);

    Page<Ticket> findPaginatedForSearchedTicket(int pageNo, int pageSize, String query);
}
