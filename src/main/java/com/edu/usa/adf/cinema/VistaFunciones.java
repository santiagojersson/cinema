package com.edu.usa.adf.cinema;

import java.util.LinkedList;
import java.util.List;

import com.edu.usa.adf.Datos.Pelicula;
import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VistaFunciones extends VerticalLayout implements View{

	private NativeSelect salas;
	private NativeSelect pelis;
	private List<Pelicula> listaPeliculas= new LinkedList<>();
	private List<Sala> listaSalas= new LinkedList<>();
	private Sala sala;
	private Pelicula pelicula;
	
	
	public VistaFunciones() {
		
		pelis = new NativeSelect("Seleccione una Pelicula");
		pelis.setNullSelectionAllowed(false);
        pelis.setImmediate(true);
      
        pelis.addValueChangeListener(e -> Notification.show("Value changed:",
                String.valueOf(e.getProperty().getValue()),
                Type.TRAY_NOTIFICATION));
        
        salas = new NativeSelect("Seleccione una Sala");
		salas.setNullSelectionAllowed(false);
        salas.setImmediate(true);
        
        salas.addValueChangeListener(e -> Notification.show("Value changed:",
                String.valueOf(e.getProperty().getValue()),
                Type.TRAY_NOTIFICATION));
        
        cargarListas();
        HorizontalLayout hori= new HorizontalLayout(pelis,salas);
        hori.setSizeUndefined();
        hori.setSpacing(true);
        addComponent(hori);
	}

	private void cargarListas() {
		listaPeliculas= Consultas.getPeliculas();
		for (Pelicula peli : listaPeliculas) {
			pelis.addItem(peli.getNombre());
			
		}
		listaSalas= Consultas.getSalas();
		for (Sala sala : listaSalas) {
			salas.addItem(sala.getSalaId());
			
		}
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("VistaFunciones");
		
	}

}
