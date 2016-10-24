package com.edu.usa.adf.Datos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name="Sala")
public class Sala {
	
	@Id 
	@GeneratedValue( strategy=GenerationType.AUTO )
	private int salaId;
	
	private int cantidadFilas;
	private int capacidadTotal;
	private int sillasDisponibles;
	
	@OneToMany(targetEntity=Silla.class)
	private List<Silla> sillas;
	
	
	public Sala(int salaId, int cantidadFilas, int capacidadTotal, int sillasDisponibles) {
		super();
		this.salaId = salaId;
		this.cantidadFilas = cantidadFilas;
		this.capacidadTotal = capacidadTotal;
		this.sillasDisponibles = sillasDisponibles;
	}

	public Sala(){
		super();
	}
	
	public int getSalaId() {
		return salaId;
	}

	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}

	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public void setCantidadFilas(int cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}

	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}

	public int getSillasDisponibles() {
		return sillasDisponibles;
	}

	public void setSillasDisponibles(int sillasDisponibles) {
		this.sillasDisponibles = sillasDisponibles;
	}

	public List<Silla> getSillas() {
		return sillas;
	}

	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}
	
	
	
}
