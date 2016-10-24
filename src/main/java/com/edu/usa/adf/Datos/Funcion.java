package com.edu.usa.adf.Datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name="Funcion")
public class Funcion {
	
	@Id 
    @GeneratedValue( strategy=GenerationType.AUTO )
	private int funtionId;
	
	private String horaInicio;
	private String horaFin;
	
	@ManyToOne
	@JoinColumn(name="peliculaId")
	private Pelicula pelicula;
	
	@ManyToOne
	@JoinColumn(name="salaId")
	private Sala sala;

	public Funcion(int id, String horaInicio, String horaFin) {
		super();
		this.funtionId = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}	
	
	public Funcion(){
		super();
	}

	public int getId() {
		return funtionId;
	}

	public void setId(int id) {
		this.funtionId = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	
	
}
