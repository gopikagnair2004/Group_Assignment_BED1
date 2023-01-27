package com.gl.ticket.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.ticket.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	 @Query(value = "select * from tickets t where t.ticket_title like %:keyword% or t.ticket_description like %:keyword%", nativeQuery = true)
	 List<Ticket> findByKeyword(@Param("keyword") String keyword);
	 
	 

}
