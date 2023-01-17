package com.example.ITTicketRecords.controller;

import com.example.ITTicketRecords.entity.Ticket;
import com.example.ITTicketRecords.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public String showHomepage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Ticket> page = ticketService.findPaginated(pageNo, pageSize);
        List<Ticket> listTickets = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listTickets", listTickets);

        int current = pageNo + 2;
        int begin = Math.max(1, current - 4);
        int end = Math.min(begin + 4, page.getTotalPages());

        model.addAttribute("current", current);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        return "tickets";
    }

    @RequestMapping(value = "/tickets/searched/{query}", method = RequestMethod.GET)
    public String showTicketsSearched(@RequestParam("query") String query, Model model) {
        return findPaginatedForSearchedTicket(1, query, model);
    }

    @GetMapping("/page/{pageNo}/searched/{query}")
    public String findPaginatedForSearchedTicket(@PathVariable(value = "pageNo") int pageNo, @PathVariable("query") String query, Model model) {
        int pageSize = 5;

        Page<Ticket> page = ticketService.findPaginatedForSearchedTicket(pageNo, pageSize, query);
        List<Ticket> listTickets = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listTickets", listTickets);

        int current = pageNo + 2;
        int begin = Math.max(1, current - 4);
        int end = Math.min(begin + 4, page.getTotalPages());

        model.addAttribute("current", current);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        return "tickets";
    }

    @GetMapping("/tickets/{status}")
    public String showOpenTickets(@PathVariable(value = "status") String currentStatus, Model model) {
        return findPaginatedWithOpenTickets(1, currentStatus, model);
    }

    @GetMapping("/page/{pageNo}/{status}")
    public String findPaginatedWithOpenTickets(@PathVariable(value = "pageNo") int pageNo,
                                               @PathVariable(value = "status") String currentStatus,
                                               Model model) {
        int pageSize = 5;

        Page<Ticket> page = ticketService.findPaginatedBasedOnStatus(pageNo, pageSize, currentStatus);
        List<Ticket> listTickets = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listTickets", listTickets);

        int current = pageNo + 2;
        int begin = Math.max(1, current - 4);
        int end = Math.min(begin + 4, page.getTotalPages());

        model.addAttribute("current", current);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        return "open_tickets";
    }

    @GetMapping("/tickets/new")
    public String createTicket(Model model){
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "create_ticket";
    }

    @PostMapping("/tickets/new")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket){
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/view/{id}")
    public String viewTicket(@PathVariable Long id, Model model){
        Ticket ticket = ticketService.getById(id);
        model.addAttribute("ticket", ticket);
        return "view_ticket";
    }

    @GetMapping("/tickets/edit/{id}")
    public String showEditTicketForm(@PathVariable Long id, Model model){
        Ticket ticket = ticketService.getById(id);
        model.addAttribute("ticket", ticket);
        return "edit_ticket";
    }

    @PostMapping("/tickets/edit/{id}")
    public String editTicket(@PathVariable Long id, Ticket ticket, Model model) {
        ticketService.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getById(id);
        ticketService.deleteTicket(ticket);
        return "redirect:/tickets";
    }

}
