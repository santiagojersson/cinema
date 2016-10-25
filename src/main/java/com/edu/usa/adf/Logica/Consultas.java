package com.edu.usa.adf.Logica;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.edu.usa.adf.Datos.Funcion;
import com.edu.usa.adf.Datos.Pelicula;
import com.edu.usa.adf.Datos.Sala;
import com.edu.usa.adf.Datos.Silla;
import com.vaadin.ui.Notification;

public class Consultas {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema");
	
	public static void agregarPelicula(String nombre, String genero, int duracion){
		EntityManager em = emf.createEntityManager();
		EntityTransaction emm=em.getTransaction();
		emm.begin();
		try {
			Pelicula peli= new Pelicula();
			peli.setNombre(nombre);
			peli.setGenero(genero);
			peli.setDuracion(duracion);
			peli.setId(getPeliculas().size()+1);
			em.persist(peli);
			em.getTransaction().commit();
			//em.close();
			Notification.show("Pelicula\n  Insertado con Exito");
		} catch (Exception e) {
			Notification.show("Pelicula\n  No se ha Insertado con Exito");
			e.printStackTrace();
		}
			
	}
	
	public static List<Sala> getSalas(){
		EntityManager em = emf.createEntityManager();//Select UPPER(e.ename) from Employee e
		Query q = em.createQuery("Select u from Sala u");//
		List<Sala> listaSalas =new LinkedList<Sala>(q.getResultList());
		return listaSalas;
		
	}
	
	public static void agregarSala(int cantidadFilas,int capacidadTotal){
		EntityManager em = emf.createEntityManager();
		EntityTransaction emm=em.getTransaction();
		emm.begin();
		List<Silla> sillas= new LinkedList<>();
		try {
		int cantidadPorFilas= capacidadTotal/cantidadFilas;
		int conta=0;
		for (int i = 0; i < cantidadFilas; i++) {
			
			for (int j = 0; j < cantidadPorFilas; j++) {
				Silla s= new Silla();
				s.setFila(i+1);
				s.setNumero(conta++);
				s.setOcupado(false);
				sillas.add(s);
				em.persist(s);
			}
		}
		
		
			Sala salita= new Sala();
			
			salita.setCantidadFilas(cantidadFilas);
			salita.setCapacidadTotal(capacidadTotal);
			salita.setSillas(sillas);
			salita.setSillasDisponibles(capacidadTotal);
			salita.setSalaId(getSalas().size()+1);
			em.persist(salita);
			em.getTransaction().commit();
			em.close();
			Notification.show("Sala\n  Insertado con Exito");
		} catch (Exception e) {
			Notification.show("Sala\n  No se ha Insertado con Exito");
			e.printStackTrace();
		}
	}

	
	
	public static List<Silla> getSillasDesocupadas(){
		EntityManager em = emf.createEntityManager();//Select UPPER(e.ename) from Employee e
		Query q = em.createQuery("Select u from Silla u where u.ocupado=:0");//
		List<Silla> listaSillas =new LinkedList<Silla>(q.getResultList());
		return listaSillas;
		
	}
	
	public static List<Silla> getSillasOcupadas(){
		EntityManager em = emf.createEntityManager();//Select UPPER(e.ename) from Employee e
		Query q = em.createQuery("Select u from Silla u where u.ocupado=:1");//
		List<Silla> listaSillas =new LinkedList<Silla>(q.getResultList());
		return listaSillas;
		
	}

	public static void borrarSala(int salaId) {
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf2.createEntityManager();
		EntityTransaction emm=em.getTransaction();
		emm.begin();
		Query q = em.createQuery("Delete from Sala r where r.salaId=:salaId");
		q.setParameter("salaId", salaId);
		q.executeUpdate();
		emm.commit();
		Notification.show("Registro Eliminado");
		
	}

	public static List<Pelicula> getPeliculas() {
		EntityManager em = emf.createEntityManager();//Select UPPER(e.ename) from Employee e
		Query q = em.createQuery("Select u from Pelicula u");//
		List<Pelicula> listapeliculas =new LinkedList<Pelicula>(q.getResultList());
		return listapeliculas;
	}

	public static void updatePelicula(Pelicula p) {
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf2.createEntityManager();
		EntityTransaction emm=em.getTransaction();
		emm.begin();
		Pelicula peli=em.find( Pelicula.class, p.getId() );
		peli.setDuracion(p.getDuracion());
		peli.setGenero(p.getGenero());
		peli.setNombre(p.getNombre());
		
		emm.commit();
		
	}

	public static void borrarPelicula(Pelicula pelicula) {
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf2.createEntityManager();
		EntityTransaction emm=em.getTransaction();
		emm.begin();
		Query q = em.createQuery("Delete from Pelicula r where r.id=:id");
		q.setParameter("id", pelicula.getId());
		q.executeUpdate();
		emm.commit();
		Notification.show("Registro Eliminado");
		
	}

	public static void agregarFuncion(String horainicio, Pelicula pelicula, Sala sala) {
		// TODO Auto-generated method stub
		  EntityManager entitymanager = emf.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );

	      Funcion funcion= new Funcion();
	      
	      int duracionpeli=pelicula.getDuracion();
	      String[] horas=horainicio.split(":");
	      
	      double finpeli=((duracionpeli*1.0)/(60*1.0));
	      System.out.println("DIVISION "+finpeli);
	      finpeli=finpeli+Double.valueOf(horas[0]+"."+horas[1]);
	      finpeli=Math.round( finpeli * 100.0 ) / 100.0;
	      funcion.setHoraFin(String.valueOf(finpeli).replace('.', ':'));
	      funcion.setHoraInicio(horainicio);
	      funcion.setPelicula(pelicula);
	      funcion.setSala(sala);
	      funcion.setId(getFunciones().size()+1);
	      
	      entitymanager.persist( funcion );
	      entitymanager.getTransaction( ).commit( );
	}

	
	public static List<Funcion> getFunciones() {
		EntityManager em = emf.createEntityManager();//Select UPPER(e.ename) from Employee e
		Query q = em.createQuery("Select u from Funcion u");//
		List<Funcion> listafunciones =new LinkedList<Funcion>(q.getResultList());
		return listafunciones;
	}
	
	
}
