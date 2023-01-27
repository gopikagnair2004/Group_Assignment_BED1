package com.gl.ticket.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ticket_title",nullable = false)
	private String ticketTitle;

	@Column(name = "ticket_description",nullable = false)
	private String ticketDescription;

	@Column(name = "ticket_content",nullable = false)
	private String ticketContent;

	
	@Temporal(TemporalType.DATE)

	@Column(name = "ticket_date",nullable = false)
	private Date ticketCreatedDate;

	@PrePersist
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private void onCreate() {
		ticketCreatedDate = new Date();
		
	}

}
