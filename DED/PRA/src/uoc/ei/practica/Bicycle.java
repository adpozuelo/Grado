package uoc.ei.practica;

import java.util.Comparator;
import java.util.Date;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;

/**
 * Método que representa una bicicleta en el sistema
 *
 */
public class Bicycle extends IdentifiedObject implements Comparable<Bicycle> {

	private String model;
	private long totalTime;
	private Lista<Service> services;
	private Service currentService;
	private Lista<Ticket> tickets; // lista de incidencias de la bicicleta
	
	/**
	 * estacion en donde esta aparcada la bicicleta
	 */
	private Station currentStation;
	
	// estados posibles de la bicicleta
	public static final String STATUS_FAULTY = "FAULTY";
	public static final String STATUS_IN_SERVICE = "IN_SERVICE";
	
	/**
	 * estado de la bicicleta
	 */
	private String status; // estado de la bicicleta
	
	/**
	 * comparador que define el orden global entre las bicicletas por su identificador
	 */
	public static Comparator<String>  COMP = new Comparator<String>() {
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}		
	};
	
	public Bicycle(String bicycleId, String model) {
		this(bicycleId);
		this.model=model;
		this.services = new ListaEncadenada<Service>();
		this.currentService=null;
		this.currentStation = null; // estacion en donde esta aparcada la bicicleta
		this.status = STATUS_IN_SERVICE;
		this.tickets = new ListaEncadenada<Ticket>(); // lista de incidencias de la bicicleta
	}

	public Bicycle(String bicycleId) {
		super(bicycleId);
	}

	public long time() {
		return this.totalTime;
	}
	
	/**
	 * método que proporcina los servicios de una bicicleta
	 * @return
	 */
	public Contenedor<Service> services() {
		return this.services;
	}

	/**
	 * método que indica el inicio de un servicio
	 * @param user usuario que utiliza la bicicleta
	 * @param station estación
	 * @param dateTime fecha en la que se inicía un servicio
	 */
	public void startService(User user, Station station, Date dateTime) {
		Service service= new Service(user, station, dateTime);
		this.services.insertarAlFinal(service);
		this.currentService=service;
		this.currentStation =  station; // asigno la estacion a la bicicleta
		user.addCurrentService(service);
	}

	/**
	 * método que indica el final de un servicio
	 * @param toStationId estación destino
	 * @param dateTime fecha en la que finaliza el servicio
	 */
	public void finishService(Station toStationId, Date dateTime) {
		long time = this.currentService.finish(toStationId, dateTime);
		User user = this.currentService.getUser();
		user.finishCurrentService();
		this.currentService=null;
		this.currentStation = toStationId; // asigno la estacion a la bicicleta
		this.totalTime+=time;
	}
	
	/**
	 * método que proporciona una representación en forma de String
	 * de una bicicleta
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer(super.toString());
		sb.append("model: ").append(this.model).append(Messages.LS);
		sb.append("time of use: ").append(DateUtils.diffHours(this.totalTime)).append(" hours");
		if (this.totalTime!=0) sb.append(" (timeMilis: ").append(this.totalTime+") ").append(Messages.LS);
		else sb.append(Messages.LS);
		if (this.currentService!=null) sb.append(Messages.RUNNING).append(Messages.LS);
		//else sb.append(Messages.PARKING).append(Messages.LS);
		return sb.toString();
	}

	/**
	 * método que define el orden natural de una bicicleta
	 */
	@Override
	public int compareTo(Bicycle arg0) {
		return this.identifier.compareTo(arg0.identifier);
	}

	/**
	 * devuelve la estacion en donde esta aparcada la bicicleta
	 * @return the currentStation
	 */
	public Station getCurrentStation() {
		return currentStation;
	}

	/**
	 * asigna el estado de la bicicleta a averiada
	 */
	public void statusToFaulty() {
		this.status = STATUS_FAULTY;
	}
	
	/**
	 * asigna el estado de la bicicleta a en servicio
	 */
	public void statusToInService() {
		this.status = STATUS_IN_SERVICE;
	}
	
	/**
	 * inserta una incidencia en la lista de incidencias
	 * @param ticket
	 * 			la incidencia a insertar
	 */
	public void insertarTicket(Ticket ticket) {
		this.tickets.insertarAlFinal(ticket);
	}
	
	/**
	 * devuelve un iterador a la lista de incidencias de la bicicleta
	 * @return
	 * 		el iterador
	 */
	public Iterador<Ticket> tickets() {
		return this.tickets.elementos();
	}
	
	/**
	 * devuelve si la bicicleta no tiene incidencias
	 * @return
	 * 		true si no hay incidencias, false en caso contrario
	 */
	public boolean ticketsEmpty() {
		return this.tickets.estaVacio();
	}

	/**
	 * devuelve el estatus de la bicicleta
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * getter para obtener el identificador de la bicicleta
	 * @return
	 * 		el identificador
	 */
	@Override
	public String getIdentifier() {
		return super.getIdentifier();
	}

	/**
	 * metodo sobreescrito equals segun el identificador de la bicicleta
	 * @return
	 * 		true si las dos bicicletas son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bicycle))
			return false;
		else
			return this.getIdentifier().equals(((Bicycle)obj).getIdentifier());
	}

	/**
	 * metodo que establece la estacion en donde esta aparcada la bicicleta
	 * @param currentStation the currentStation to set
	 */
	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	/**
	 * devuelve el tiempo total de uso de la bicicleta
	 * @return the totalTime
	 */
	public long getTotalTime() {
		return totalTime;
	}	
}
