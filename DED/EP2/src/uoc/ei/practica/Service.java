package uoc.ei.practica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class Service {
	
	private User usuario; // el usuario que realiza el servicio
	private Station stationFrom; // la estacion que realiza el servicio de origen
	private Station stationTo; // la estacion que realiza el servicio de destino
	private Date date_ini; // la fecha inicio del servicio
	private Date date_fin; // la fecha final del servivio
	
	/**
	 * Constructor
	 * @param usuario
	 * 				el usuario que realiza el servicio
	 * @param stationFrom
	 * 				la estacion que realiza el servicio
	 * @param date_ini
	 * 				la fecha inicio del servicio
	 * @param date_fin
	 * 				la fecha final del servivio
	 */
	public Service(User usuario, Station stationFrom, Date date_ini) {
		super();
		this.setUsuario(usuario); // inicializo el usuario que hace el servicio
		this.setStationFrom(stationFrom);  // incializo la estacion de origen del servicio
		this.setDate_ini(date_ini); // inicializo la fecha de origen del servicio
		this.setDate_fin(null); // incializo a null la fecha final del servicio ya que no la conocemos en el momento de la instanciacion
		this.setStationTo(null); // incializo a null la estacion final del servicio ya que no la conocemos en el momento de la instanciacion
	}
	
	/**
	 * metodo sobre-escrito toString
	 */
	@Override
	public String toString() {
		// instancio el formato de la fecha especificada en el juego de pruebas
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		// retorno el string en el formato especificado en el juego de pruebas
		return new String("\nuser: " + this.getUsuario().getName() + " (" + this.getUsuario().getId() + ")"
				+ "\nfrom: STATION" + this.getStationFrom().getId() + "\nto: STATION" + this.getStationTo().getId()
				+ "\nstartTime: " +  dateFormat.format(getDate_ini()) + "\nendTime: " + dateFormat.format(getDate_fin()) + "\n");
	}



	/**
	 * @return el usuario que realiza el servicio
	 */
	public User getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario
	 * 				el usuario que realiza el servicio
	 */
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return la estacion que realiza el servicio
	 */
	public Station getStationFrom() {
		return stationFrom;
	}
	/**
	 * @param station
	 * 				la estacion que realiza el servicio
	 */
	public void setStationFrom(Station station) {
		this.stationFrom = station;
	}
	/**
	 * @return la fecha inicio del servicio
	 */
	public Date getDate_ini() {
		return date_ini;
	}
	/**
	 * @param date_ini
	 * 				la fecha inicio del servicio
	 */
	public void setDate_ini(Date date_ini) {
		this.date_ini = date_ini;
	}
	/**
	 * @return la fecha final del servivio
	 */
	public Date getDate_fin() {
		return date_fin;
	}
	/**
	 * @param date_fin
	 * 				la fecha final del servivio
	 */
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	/**
	 * @return la estacion que realiza el servicio de destino
	 */
	public Station getStationTo() {
		return stationTo;
	}

	/**
	 * @param stationTo
	 * 				la estacion que realiza el servicio de destino
	 */
	public void setStationTo(Station stationTo) {
		this.stationTo = stationTo;
	}

}
