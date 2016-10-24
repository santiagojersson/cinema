package com.edu.usa.adf.cinema;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VistaMain extends VerticalLayout implements View{
	
	
	
	public VistaMain(Navigator navigator) {
        setSizeFull();
        Button btnSalas = new Button("Salas");
		Button btnPeliculas = new Button("Pelicula");
		Button btnFunciones = new Button("Funciones");
		Button btnComprarTicket = new Button("Comprar Ticket");
		Image im = new Image();
		
		HorizontalLayout ho= new HorizontalLayout();
		ho.addComponents(btnSalas, btnPeliculas, btnFunciones,btnComprarTicket);
		im.setSource(new ThemeResource("img/CinemaForAllLogo.jpg"));
		im.setVisible(true);
		
		btnSalas.addClickListener(e ->navigator.navigateTo("salas"));
		btnPeliculas.addClickListener(e ->navigator.navigateTo("peli"));
		btnFunciones.addClickListener(e ->navigator.navigateTo("funciones"));
		btnComprarTicket.addClickListener(e ->navigator.navigateTo("ticket"));
        
        addComponent(im);
		addComponent(ho);
        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

	public void enter(ViewChangeEvent event) {
		
		System.out.println("mainView");
		
	}

	
}