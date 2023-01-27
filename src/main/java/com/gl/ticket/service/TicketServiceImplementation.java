package com.gl.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ticket.dao.TicketRepository;
import com.gl.ticket.entity.Ticket;

@Service
public class TicketServiceImplementation implements TicketService{

	@Autowired
	private final TicketRepository ticketRepository;

	TicketServiceImplementation(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = this.ticketRepository.findAll();
		return tickets;
	}
	
	@Override 
	public List<Ticket> getByKeyword(String keyword){
		return this.ticketRepository.findByKeyword(keyword);
		 }

	@Override
	public Ticket saveTicket(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketById(Long id) {
		return this.ticketRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("invalid ticket id passed"));

	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	@Override
	public void deleteTicketById(Long id) {
		this.ticketRepository.deleteById(id);

	}
	
	

}
