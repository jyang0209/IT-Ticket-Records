package com.example.ITTicketRecords.service.impl;

import com.example.ITTicketRecords.entity.Ticket;
import com.example.ITTicketRecords.repository.TicketRepository;
import com.example.ITTicketRecords.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    @Override
    public Page<Ticket> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("requestedTime").descending());
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Page<Ticket> findPaginatedBasedOnStatus(int pageNo, int pageSize, String currentStatus) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("requestedTime").descending());
        return ticketRepository.findByStatusOrderByRequestedTime(currentStatus, pageable);
    }

    @Override
    public Page<Ticket> findPaginatedForSearchedTicket(int pageNo, int pageSize, String query) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("requestedTime").descending());
        return ticketRepository.searchTickets(query ,pageable);
    }

}
