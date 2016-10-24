package com.edu.usa.adf.Datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name="Ticket")
public class Ticket {
	
	@Id 
    @GeneratedValue( strategy=GenerationType.AUTO )
	private int ticketId;
	private String silla;
	
	@ManyToOne
	@JoinColumn(name="funtionId")
	private Funcion funcion;

	public Ticket(int id, String silla, Funcion funcion) {
		super();
		this.ticketId = id;
		this.silla = silla;
		this.funcion = funcion;
	}

	public Ticket(){
		super();
	}

	public int getId() {
		return ticketId;
	}

	public void setId(int id) {
		this.ticketId = id;
	}

	public String getSilla() {
		return silla;
	}

	public void setSilla(String silla) {
		this.silla = silla;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}
	
	
	
}
