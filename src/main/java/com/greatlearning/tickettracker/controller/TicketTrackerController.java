package com.greatlearning.tickettracker.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.tickettracker.entity.Ticket;
import com.greatlearning.tickettracker.services.TrackerService;

@Controller
@RequestMapping("/admin/tickets")
public class TicketTrackerController {

	@Autowired
	private TrackerService trackerService;

	@GetMapping(value = { "", "/" })
	public String getAllTickets(Model model) {
		Set<Ticket> ticketsList = new LinkedHashSet<>(trackerService.getAllTickets());
		model.addAttribute("tickets", ticketsList);
		model.addAttribute("search", "");
		return "tickets";

	}

	@PostMapping("/search")
	public String searchTicket(@ModelAttribute("search") String keyword, Model model) {
		List<Ticket> ticketsList = null;
		if (!StringUtils.hasText(keyword)) {
			ticketsList = trackerService.getAllTickets();
		} else {
			ticketsList = trackerService.findTicketsBykeyword(keyword);
		}
		Set<Ticket> ticketList = new LinkedHashSet<>(ticketsList);
		model.addAttribute("search", keyword);
		model.addAttribute("tickets", ticketList);
		return "tickets";
	}

	@GetMapping("/addnew")
	public String createTicketForm(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "create_ticket";
	}

	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		trackerService.saveOrUpdateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/{id}/edit")
	public String editTicketForm(@PathVariable("id") int id, Model model) {
		Ticket ticket = trackerService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "edit_ticket";
	}

	@GetMapping("/{id}/view")
	public String viewTicketForm(@PathVariable("id") int id, Model model) {
		Ticket ticket = trackerService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "view_tickets";
	}

	@GetMapping("/{id}/delete")
	public String deleteTicket(@PathVariable("id") String id) {
		trackerService.deleteTicketById(Integer.parseInt(id));
		return "redirect:/admin/tickets";
	}

	@PostMapping("/{id}")
	public String updateTicket(@PathVariable("id") String id, @ModelAttribute("ticket") Ticket updatedTicket)
			throws Exception {
		Ticket ticket = trackerService.getTicketById(Integer.parseInt(id));
		if (null != ticket) {
			ticket = new Ticket(updatedTicket);
			ticket.setId(Integer.parseInt(id));
		} else {
			throw new Exception(
					"Ticket with provided Ticket Id does not exists in the database, Please Add the Ticket before editing ");
		}
		trackerService.saveOrUpdateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/healthCheck")
	public String healthCheck() {
		return "UP";

	}

}
