package com.gl.ticket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.ticket.entity.Ticket;
import com.gl.ticket.service.TicketService;

@Controller
@RequestMapping("/admins")
public class TicketController {

	public final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/tickets")
	public String listemployees(Model model) {
		model.addAttribute("tickets", this.ticketService.getAllTickets());
		return "tickets";
	}

	@GetMapping("/tickets/new")
	public String createTicketsForm(Model model) {
		// create employee object to hold employee form data
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "new_ticket";
	}

	@PostMapping("/tickets")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.saveTicket(ticket);
		return "redirect:/admins/tickets";
	}

	@GetMapping("/tickets/edit/{id}")
	public String editTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "edit_ticket";
	}

	@GetMapping("/tickets/view/{id}")
	public String viewTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "view_ticket";
	}

	@GetMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {
		ticketService.deleteTicketById(id);
		return "redirect:/admins/tickets";
	}

	@PostMapping("/tickets/{id}")
	public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {

		// get employee from database by id
		Ticket existingTicket = ticketService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicketTitle(ticket.getTicketTitle());
		existingTicket.setTicketDescription(ticket.getTicketDescription());
		existingTicket.setTicketContent(ticket.getTicketContent());

		// save updated employee object
		ticketService.updateTicket(existingTicket);
		return "redirect:/admins/tickets";
	}

	@GetMapping(path = { "/", "/search" })
	public String searchTicket(Ticket ticket, Model model, String keyword) {
		if (keyword != null) {
			List<Ticket> ticket1 = ticketService.getByKeyword(keyword);
			model.addAttribute("tickets", ticket1);
		} else {
			List<Ticket> ticketAll = ticketService.getAllTickets();
			model.addAttribute("tickets", ticketAll);
		}
		return "tickets";
	}

}
