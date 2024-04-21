package com.greatlearning.tickettracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.tickettracker.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByTitleContaining(String keyword);
    
    List<Ticket> findByShortdescriptionContaining(String keyword);
}
