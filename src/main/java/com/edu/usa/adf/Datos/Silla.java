package com.edu.usa.adf.Datos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.criteria.Fetch;


@Entity
@TableGenerator(name="Silla")
public class Silla {
	
	@Id
    @GeneratedValue( strategy= GenerationType.AUTO ) 
	private int idSilla;

	private int numero;
	private int fila;
	private boolean ocupado;
	
	public Silla(int id, int numero, int fila, boolean ocupado) {
		super();
		this.idSilla = id;
		this.numero = numero;
		this.fila = fila;
		this.ocupado = ocupado;
	}
	
	public Silla(){
		super();
	}

	

	public int getIdSilla() {
		return idSilla;
	}

	public void setIdSilla(int idSilla) {
		this.idSilla = idSilla;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	
	
	
}
