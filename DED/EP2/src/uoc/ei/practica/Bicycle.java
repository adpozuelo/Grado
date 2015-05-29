package uoc.ei.practica;

import java.util.Date;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class Bicycle implements Comparable<Bicycle> {

	private String id; // el identificador de la bicicleta
	private String model; // el modelo de la bicicleta
	
	private Lista<Service> listServices; // lista encadenada con los servicios que ha hecho la bicicleta
	private int n_services; // el numero de servicios que ha hecho la bicicleta
	private Date timeOfUse; // el tiempo de uso en milisegundos
	private Service activeService; // el servicio activo que tiene la bicicleta
	
	/**
	 * Constructor de busqueda
	 * solo se utiliza para instanciar bicicletas con el objeto de realizar las busquedas dicotomicas
	 * @param id
	 * 			el identificador de la bicicleta
	 */
	public Bicycle(String id) {
		this(id, ""); // solo inicializo el identificador de la bicicleta ya que es el atributo mediante el que comparo bicicletas
	}
	
	/**
	 * Contructor
	 * @param id
	 *            el identificador de la bicicleta
	 * @param model
	 *            el modelo de la bicicleta
	 */
	public Bicycle(String id, String model) {
		super();
		this.setId(id); // inicializo el identificador
		this.setModel(model); // inicializo el modelo
		this.listServices = new ListaEncadenada<Service>(); // creo la lista de servicios vacia
		this.setN_services(0); // inicializo a cero el numero de servicios
		this.setTimeOfUse(new Date(0)); // inicializo a cero el tiempo de uso de la bicicleta
		this.setActiveService(null); // inicializo a nulo el servicio activo de la bicicleta
	}
	
	/**
	 * los servicios que ha hecho la bicicleta
	 * @return
	 * 			iterador a los servicios que ha hecho la bicicleta
	 */
	public Iterador<Service> servicesByBicyclete() {
		Iterador<Service> it = this.listServices.elementos(); // obtengo el iterador de la lista
		return it; // devuelvo el iterador
	}
	
	/**
	 * asigna un servicio activo a una bicicleta
	 * @param service
	 * 				el servicio a asignar
	 */
	public void activateService(Service service) {
		this.setActiveService(service); // asigno el servicio como activo a la bicicleta
	}
	
	/**
	 * al servicio activo
	 * añade la estacion de destino
	 * deriva el tiempo de uso del servicio y lo añade al tiempo de uso de la bicicleta
	 * añade el servicio a la lista de servicios de la bicicleta
	 * desasigna un servicio activo a una bicicleta
	 */
	public void deactivateService(Date t_final, Station stationTo) {
		this.getActiveService().setStationTo(stationTo); // al servicio activo le asigno la estacion destino
		this.getActiveService().setDate_fin(t_final); // al servicio activo le asigno la fecha final (entrega)
		// derivo el tiempo de uso de la bicicleta para este servicio
		long milis = this.getActiveService().getDate_fin().getTime() - this.getActiveService().getDate_ini().getTime();
		this.timeOfUse.setTime(this.timeOfUse.getTime() + milis); // actualizo el tiempo de uso general de la bicicleta
		this.addService(this.getActiveService()); // añado el servicio finalizado a la lista de servicios de la bicicleta
		this.setActiveService(null); // libero el servicio activo de la bicicleta
	}

	/**
	 * insertamos un servicio en la lista de servicios de la bicicleta
	 * @param service
	 * 				el servicio a insertar
	 */
	private void addService(Service service) {
		this.listServices.insertarAlFinal(service); // añado el serivicio a la lista de servicios
		this.setN_services(this.getN_services()+1); // añado 1 al numero de servicios de la bicicleta
	}
	
	/**
	 * @return el identificador de la bicicleta
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            el identificador de la bicicleta
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return el modelo de la bicicleta
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            el modelo de la bicicleta
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * metodo equals sobre-escrito
	 * metodo necesario para saber si una bicicleta es igual a otra
	 * @return
	 * 		true si los dos elementos son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Bicycle) { // si el objeto del parametro es un bicicleta
			// retorno el resultado de comparar los dos identificadores de las bicicletas
			// si son iguales se retorna true, el caso contrario se retorna false
			return this.getId().equals(((Bicycle)obj).getId());
		} else { // si el objeto no es una bicicleta
			return false; // retorno false
		}
	}

	/**
	 * metodo toString sobre-escrito
	 */
	@Override
	public String toString() {
		String tmp = ""; // instancio un string vacio para realizar dos salidas diferentes segun los milisegundos
		long hours = this.getTimeOfUse().getTime() / 3600000; // derivo el numero de horas de uso de la bicicleta
		if (this.getTimeOfUse().getTime() > 0) // si el numero de milisegundos de uso es mayor que cero
			tmp += hours + " hours (timeMilis: " + this.getTimeOfUse().getTime() + ")\n"; // añado las horas y los milisegundos al string
		else // en caso contrario, los milisegundos son cero
			tmp += hours + " hours\n"; // añado solo las horas al string
		// retorno el string con el formato especificado segun el juego de pruebas
		return new String("\nid: " + this.getId() + "\nmodel: " + this.getModel() + "\ntime of use: " + tmp + (this.getActiveService() != null ? "Running\n" : ""));
	}

	/**
	 * metodo compareTo sobre-escrito
	 * metodo para comparar dos bicicletas
	 */
	@Override
	public int compareTo(Bicycle o) {
		return this.getId().compareTo(o.getId()); // retorno la comparacion entre los identificadores
	}

	/**
	 * @return el numero de servicios que ha hecho la bicicleta
	 */
	public int getN_services() {
		return n_services;
	}

	/**
	 * @param n_services 
	 * 				 	el numero de servicios que ha hecho la bicicleta
	 */
	public void setN_services(int n_services) {
		this.n_services = n_services;
	}

	/**
	 * @return el tiempo de uso en milisegundos
	 */
	public Date getTimeOfUse() {
		return timeOfUse;
	}

	/**
	 * @param timeOfUse
	 * 				el tiempo de uso en milisegundos
	 */
	public void setTimeOfUse(Date timeOfUse) {
		this.timeOfUse = timeOfUse;
	}

	/**
	 * @return el servicio activo que tiene la bicicleta
	 */
	public Service getActiveService() {
		return activeService;
	}

	/**
	 * @param activeService
	 * 					el servicio activo que tiene la bicicleta
	 */
	public void setActiveService(Service activeService) {
		this.activeService = activeService;
	}
	
	
}
