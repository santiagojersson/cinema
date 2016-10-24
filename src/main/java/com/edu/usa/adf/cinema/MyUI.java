package com.edu.usa.adf.cinema;

import javax.servlet.annotation.WebServlet;

import com.edu.usa.adf.Logica.Consultas;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	
	protected static final String MAINVIEW = "main";
	 Navigator navigator;
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("Navigation Example");

		// Create a navigator to control the views
		navigator = new Navigator(this, this);

		// Create and register the views
		System.out.println("MyUI");
		navigator.addView("salas", new VistaSalas());
		navigator.addView("peli", new VistaPelicula());
		navigator.addView("funciones", new VistaFunciones());
		navigator.addView("ticket", new VistaComprar());
		navigator.addView("", new VistaMain(navigator));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
