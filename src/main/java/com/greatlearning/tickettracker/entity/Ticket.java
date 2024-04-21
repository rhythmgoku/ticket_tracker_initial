package com.greatlearning.tickettracker.entity;


import java.sql.Clob;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String shortdescription;
	
	@Lob
	private String content;
	
	private String createdOn;
	
	private String editedOn;

	public Ticket(Ticket ticket) {
		super();
		this.title = ticket.getTitle();
		this.shortdescription = ticket.getShortdescription();
		this.content = ticket.getContent();
		this.createdOn = ticket.getCreatedOn();
	}
	
	

}
