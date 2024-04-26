package com.greatlearning.tickettracker.services;

import java.util.List;

import com.greatlearning.tickettracker.entity.Ticket;

public interface TrackerService {

	List<Ticket> getAllTickets();

	Ticket saveOrUpdateTicket(Ticket ticket);

	void deleteTicketById(int int1);

	Ticket getTicketById(int id);

	List<Ticket> findTicketsBykeyword(String keyword);

}
