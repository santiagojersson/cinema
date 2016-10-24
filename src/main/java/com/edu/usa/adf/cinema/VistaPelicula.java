package com.edu.usa.adf.cinema;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.edu.usa.adf.Datos.Pelicula;
import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VistaPelicula extends VerticalLayout implements View {
	
	private TextField name= new TextField();
	private TextField genero= new TextField();
	private TextField duracion= new TextField();
	private Button crear= new Button("Crear");
	private Button borrar= new Button("Borrar");
	private Button update= new Button("Actualizar");
	private Pelicula pelicula;
	private Grid grid = new Grid();
	
	public VistaPelicula(){
		
		 name.setCaption("Nombre Pelicula:");
		 genero.setCaption("Genero de la Pelicula");
		 duracion.setCaption("Duracion en minutos:");
		 update.setVisible(false);
		 borrar.setVisible(false);
		 
		 HorizontalLayout main = new HorizontalLayout(grid);
			main.setSpacing(true);
			main.setSizeFull();
			grid.setSizeFull();
			main.setExpandRatio(grid, 1);
			HorizontalLayout horizontal= new HorizontalLayout(crear,borrar,update);
			horizontal.setSpacing(true);
			setSpacing(true);
			main.setSizeUndefined();
			addComponents(name,genero,duracion,horizontal);
			addComponents(grid, main);
			
			crear.addClickListener(e->crearPelicula());
			borrar.addClickListener(e->borrarPelicula());
			update.addClickListener(e->updatePelicula());
			grid.addSelectionListener(e->selectUpdatePelicula(e));
			
			grid.setColumns("nombre", "genero", "duracion");
			actualizarGrid();
		 
		  
		  setMargin(true);
          setSpacing(true);
		  
		  //setContent(layout);
		 
	}
	

	private void updatePelicula() {
		pelicula.setDuracion(Integer.parseInt(duracion.getValue()));
		pelicula.setGenero(genero.getValue());
		pelicula.setNombre(name.getValue());
		Consultas.updatePelicula(pelicula);
		crear.setVisible(true);
		borrar.setVisible(false);
		update.setVisible(false);
		actualizarGrid();
	}


	private void borrarPelicula() {
		Consultas.borrarPelicula(pelicula);
		crear.setVisible(true);
		borrar.setVisible(false);
		update.setVisible(false);
		actualizarGrid();
	}


	private void crearPelicula() {
		// TODO Auto-generated method stub
		int dura= Integer.parseInt(duracion.getValue());
		Consultas.agregarPelicula(name.getValue(),genero.getValue(),dura);
		actualizarGrid();
		name.setValue("");
		genero.setValue("");
		duracion.setValue("");
	}
	
	private void selectUpdatePelicula(SelectionEvent e){
		if (e.getSelected().isEmpty()) {
			update.setVisible(false);
			borrar.setVisible(false);
		} else {
			
			crear.setVisible(false);
			borrar.setVisible(true);
			update.setVisible(true);
			pelicula= (Pelicula) e.getSelected().iterator().next();
			name.setValue((pelicula.getNombre()));
			genero.setValue((pelicula.getGenero()));
			duracion.setValue(String.valueOf(pelicula.getDuracion()));
			
		}
	}
	
	public void actualizarGrid(){
		try {
			List<Pelicula> pelis = Consultas.getPeliculas();
		    grid.setContainerDataSource(new BeanItemContainer<>(Pelicula.class, pelis));
		} catch (Exception e) {
			System.out.println("No hay datos que cargar");
		}
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
}
