package com.edu.usa.adf.cinema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.edu.usa.adf.Datos.Funcion;
import com.edu.usa.adf.Datos.Pelicula;
import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VistaFunciones extends VerticalLayout implements View {

	private NativeSelect salas;
	private NativeSelect pelis;
	private NativeSelect horaInicio;
	private NativeSelect minutoInicio;
	private List<Pelicula> listaPeliculas = new LinkedList<>();
	private List<Sala> listaSalas = new LinkedList<>();
	private Sala sala;
	private Pelicula pelicula;
	private Funcion funcion;
	private String horaIni;
	private String minutoIni;
	private Button agregarFuncion = new Button("Agregar Funcion");
	private Grid grid = new Grid();

	public VistaFunciones() {

		pelis = new NativeSelect("Seleccione una Pelicula");
		pelis.setNullSelectionAllowed(false);
		pelis.setImmediate(true);

		pelis.addValueChangeListener(e -> peliculaSelected(e));

		salas = new NativeSelect("Seleccione una Sala");
		salas.setNullSelectionAllowed(false);
		salas.setImmediate(true);

		grid.setSizeFull();
		grid.setColumns("Pelicula", "Sala", "HoraInicio", "HoraFin");

		salas.addValueChangeListener(e -> salaSelected(e));

		horaInicio = new NativeSelect("Hora Inicio de la Funcion");
		horaInicio.setNullSelectionAllowed(false);
		horaInicio.setImmediate(true);
		minutoInicio = new NativeSelect("Minuto Inicio de la Funcion");
		minutoInicio.setNullSelectionAllowed(false);
		minutoInicio.setImmediate(true);

		horaInicio.addValueChangeListener(e -> selectedHora(e));
		minutoInicio.addValueChangeListener(e -> selectedMinuto(e));
		agregarFuncion.addClickListener(e -> agregarFuncion());

		actualizarGrid();
		HorizontalLayout hori = new HorizontalLayout(pelis, salas);
		HorizontalLayout horas = new HorizontalLayout(horaInicio, minutoInicio);
		hori.setSizeUndefined();
		hori.setSpacing(true);
		addComponent(hori);
		addComponent(horas);
		addComponent(agregarFuncion);
		addComponent(grid);

	}

	public void actualizarGrid() {
		try {
			List<Funcion> pelis = Consultas.getFunciones();
			for (int i = 0; i < pelis.size(); i++) {
						grid.addRow(
						pelis.get(i).getPelicula().getNombre(),
						String.valueOf(pelis.get(i).getSala().getSalaId()),
						pelis.get(i).getHoraInicio(), 
						pelis.get(i).getHoraFin()
						);
			}

			// grid.setContainerDataSource(new
			// BeanItemContainer<>(Funcion.class, pelis));
		} catch (Exception e) {
			System.out.println("No hay datos que cargar");
		}

	}

	private void agregarFuncion() {
		try {
			String horainicio = horaIni + ":" + minutoIni;

			Consultas.agregarFuncion(horainicio, pelicula, sala);
			actualizarGrid();
		} catch (Exception e) {
			Notification.show("Por favor seleccione todos los datos");
		}

	}

	private void selectedMinuto(ValueChangeEvent e) {
		minutoIni = (String) e.getProperty().getValue().toString().trim();
	}

	private void selectedHora(ValueChangeEvent e) {
		horaIni = (String) e.getProperty().getValue().toString().trim();
	}

	private void cargarHoras() {
		for (int i = 0; i < 24; i++) {
			horaInicio.addItem(i + 1);
		}
		int i = 0;
		while (i <= 60) {
			minutoInicio.addItem(i);
			i = i + 5;
		}
	}

	private void salaSelected(ValueChangeEvent e) {
		String s = e.getProperty().getValue().toString().trim();
		for (Sala salita : listaSalas) {
			if (salita.getSalaId() == Integer.parseInt(s)) {
				sala = salita;
				break;
			}
		}
	}

	private void peliculaSelected(ValueChangeEvent e) {
		String p = e.getProperty().getValue().toString().trim();
		for (Pelicula peli : listaPeliculas) {
			if (peli.getNombre().equalsIgnoreCase(p)) {
				pelicula = peli;
				break;
			}
		}
	}

	private void cargarListas() {
		listaPeliculas = Consultas.getPeliculas();
		for (Pelicula peli : listaPeliculas) {
			pelis.addItem(peli.getNombre());

		}
		listaSalas = Consultas.getSalas();
		for (Sala sala : listaSalas) {
			salas.addItem(sala.getSalaId());

		}

	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("VistaFunciones");
		sala = null;
		pelicula = null;
		cargarHoras();
		cargarListas();
	}

}
