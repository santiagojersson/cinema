package com.edu.usa.adf.cinema;

import java.util.List;

import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class VistaSalas extends VerticalLayout implements View{
	
	private Grid grid = new Grid();
	
	private TextField cantidadFilas=new TextField();
	private TextField capacidad=new TextField();
	
	private Button crear= new Button("Crear");
	private Button borrar= new Button("Borrar");
	//private Button update= new Button("Actualizar");
	private Sala sala;
	
	
	public VistaSalas() {
		cantidadFilas.setCaption("Cantidad de Filas");
		capacidad.setCaption("Capacidad");
		
		borrar.setVisible(false);
		HorizontalLayout main = new HorizontalLayout(grid);
		main.setSpacing(true);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);
		HorizontalLayout horizontal= new HorizontalLayout(crear,borrar);
		horizontal.setSpacing(true);
		setSpacing(true);
		main.setSizeUndefined();
		addComponents(cantidadFilas,capacidad,horizontal);
		addComponents(grid, main);
		
		crear.addClickListener(e->crearSalon());
		borrar.addClickListener(e->borrarSala());
		//update.addContextClickListener(e->updateSalon());
		grid.addSelectionListener(e->selectUpdateSala(e));
		
		 grid.setColumns("salaId", "cantidadFilas", "capacidadTotal","sillasDisponibles");
	    // fetch list of Customers from service and assign it to Grid
	    
		actualizarGrid();
	    setMargin(true);
	    
	}
	
	

	private void crearSalon() {
		// TODO Auto-generated method stub
		int cantiFilas= Integer.parseInt(cantidadFilas.getValue());
		int capaci= Integer.parseInt(capacidad.getValue());
		Consultas.agregarSala(cantiFilas, capaci);
		actualizarGrid();
	}
	
	
	private void selectUpdateSala(SelectionEvent e){
		if (e.getSelected().isEmpty()) {
			borrar.setVisible(false);
		} else {
			
			crear.setVisible(false);
			borrar.setVisible(true);
			sala= (Sala) e.getSelected().iterator().next();
			cantidadFilas.setValue(String.valueOf(sala.getCantidadFilas()));
			capacidad.setValue(String.valueOf(sala.getCapacidadTotal()));
			
		}
	}
	
	private void borrarSala() {
		Consultas.borrarSala(sala.getSalaId());
		borrar.setVisible(false);
		crear.setVisible(true);
		actualizarGrid();
	}
	
	public void actualizarGrid(){
		try {
			List<Sala> salas = Consultas.getSalas();
		    grid.setContainerDataSource(new BeanItemContainer<>(Sala.class, salas));
		} catch (Exception e) {
			System.out.println("No hay datos que cargar");
		}
		
	}
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("VistASalas");
		
	}

}
