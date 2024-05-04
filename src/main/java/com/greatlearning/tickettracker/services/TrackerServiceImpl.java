package com.greatlearning.tickettracker.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.tickettracker.entity.Ticket;
import com.greatlearning.tickettracker.repository.TicketRepository;

@Service
public class TrackerServiceImpl implements TrackerService {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Ticket saveOrUpdateTicket(Ticket ticket) {
		Date date = new Date();
		if (ticket.getId() > 0) {
			ticket.setEditedOn(DATE_FORMAT.format(date));
		} else {
			ticket.setCreatedOn(DATE_FORMAT.format(date));
			ticket.setEditedOn(DATE_FORMAT.format(date));
		}
		return ticketRepository.save(ticket);
	}

	@Override
	public void deleteTicketById(int id) {
		ticketRepository.deleteById(id);

	}

	@Override
	public Ticket getTicketById(int id) {
		return ticketRepository.findById(id).get();

	}

	@Override
	public List<Ticket> findTicketsBykeyword(String keyword) {
		List<Ticket> tickets = new ArrayList<>();
		tickets.addAll(ticketRepository.findByShortdescriptionContainingOrderByIdAsc(keyword));
		tickets.addAll(ticketRepository.findByTitleContainingOrderByIdAsc(keyword));
		return tickets;

	}

}
