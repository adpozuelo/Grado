package uoc.ei.practica;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class Station {

	private int id; // el identificador de la estacion
	private long latitude; // la latitud de la estacion
	private long longitude; // la longitud de la estacion
	private int nParkings; // el numero de plazas de la estacion
	private int servicesUsed; // el numero de servicios realizados por la estacion
	
	private int nParkins_used; // el numero de plazas de la estacion utilizadas
		
	private Lista<Bicycle> listParkedBicycles; // lista de las bicicletas aparcadas

	/**
	 * Constructor
	 * 
	 * @param id
	 *            el identificador de la estacion
	 * @param latitude
	 *            la latitud de la estacion
	 * @param longitude
	 *            la longitud de la estacion
	 * @param nParkings
	 *            el numero de plazas de la estacion
	 */
	public Station(int id, long latitude, long longitude, int nParkings) {
		super();
		try { // dado que el id es un numero natural, lo controlo con un try/catch
			this.setId(id); // si al llamar al setter el id es menor que cero
		} catch (EIException e) { // recojo la excepcion
			e.printStackTrace(); // e imprimo la salida
		}
		this.setLatitude(latitude); // inicializo la latitud
		this.setLongitude(longitude); // inicializo la longitud
		this.setnParkings(nParkings); // inicializo el numero de plazas de aparcamiento
		this.setnParkins_used(0); // inicializo a cero el numero de plazas ocupadas
		this.listParkedBicycles = new ListaEncadenada<Bicycle>(); // istancio la lista encadenada de bicicletas aparcadas
		this.setServicesUsed(0); // inicializo a cero el numero de servicios realizados
	}

	/**
	 * devuelve la bicicleta menos utilizada de la lista de bicicletas
	 * @return
	 * 			la bicicleta menos utilizada
	 */
	public Bicycle getLessUsedBicycle() {
		Posicion<Bicycle> posLessUsed = null; // posicion de la bicicleta menos utilizada
		Posicion<Bicycle> pos = null; // posicion dentro del recorrido
		Recorrido<Bicycle> rec = this.listParkedBicycles.posiciones(); // recorrido por la lista de usuarios
		while (rec.haySiguiente()) { // mientras existan bicicletas en el recorrido
			pos = rec.siguiente(); // pasamos a la siguiente
			//si la bicicleta menos utiliza es nula o si el tiempo de dicha bicicleta es mayor que la de la actual iteracion
			if (posLessUsed == null || posLessUsed.getElem().getTimeOfUse().getTime() > pos.getElem().getTimeOfUse().getTime()) {
				posLessUsed = pos; // asigno a la bicicleta menos utilizada la bicicleta de la iteracion
			}
		}
		Bicycle bicycleLessUsed = posLessUsed.getElem(); // obtengo la bicicleta menos utilizada
		this.listParkedBicycles.borrar(posLessUsed); // borro la posicion de la bicicleta menos utilizada de la lista
		this.setnParkins_used(this.getnParkins_used()-1); // resto uno al numero de plazas ocupadas
		this.setServicesUsed(this.getServicesUsed()+1); // sumo uno al numero de servicios de la estacion
		return bicycleLessUsed; // devuelvo la bicicleta menos utilizada
	}
	
	/**
	 * las bicicletas aparcadas en la estacion
	 * @return
	 * 		un interador a la lista de bicicletas aparcadas en la estacion
	 */
	public Iterador<Bicycle> bicyclesParked() {
		return this.listParkedBicycles.elementos(); // devuelvo el iterador de la lista de bicicletas aparcadas
	}
	
	/**
	 * nos dice si una estacion tiene todas las plazas de aparcamiento ocupadas
	 * @return
	 * 		true si la estacion esta llena, false en caso contrario
	 */
	public boolean isStationFull() {
		// si el numero de plazas de parking es igual al numero de plazas ocupadas
		// devuelvo true, en caso contrario false
		return this.getnParkings() == this.getnParkins_used();
	}
	
	/**
	 * añade una bicicleta a la lista de bicicletas aparcadas en la estacion
	 * @param b
	 * 		la bicicleta a añadir en la lista
	 */
	public void addBicycle(Bicycle b) {
		this.listParkedBicycles.insertarAlFinal(b); // añado la bicicleta al final de la lista
		this.setnParkins_used(this.getnParkins_used()+1); // sumo uno al contador de plazas ocupadas
	}
	
	/**
	 * @return el identificador de la estacion
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            el identificador de la estacion
	 * @throws EIException
	 *             en caso de que el id no sea un numero natural
	 */
	public void setId(int id) throws EIException {
		if (id < 0) { // si el id es menor que cero, el numero no es natural
			throw new EIException("Invalid station id"); // lanzo excepcion
		} else { // en caso contrario, el numero es natural
			this.id = id; // asigno el id correcto
		}
	}

	/**
	 * @return la latitud de la estacion
	 */
	public long getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            la latitud de la estacion
	 */
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return la longitud de la estacion
	 */
	public long getLongitude() {
		return longitude;

	}

	/**
	 * @param longitude
	 *            la longitud de la estacion
	 */
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return el numero de plazas de la estacion
	 */
	public int getnParkings() {
		return nParkings;
	}

	/**
	 * @param nParkings
	 *            el numero de plazas de la estacion
	 */
	public void setnParkings(int nParkings) {
		this.nParkings = nParkings;
	}

	/**
	 * metodo toString sobre-escrito
	 * @return string con los datos de la estacion
	 */
	@Override
	public String toString() {
		// retorno un string con el formato especificado en el juego de pruebas
		return new String("id: STATION" + this.getId() + "\nlatitude: "
					+ this.getLatitude() + "\nlongitude: " + this.getLongitude()
					+ "\nnParkings: " + this.getnParkings() + "\nbicycles: " + this.getnParkins_used() + "\n");
	}

	/**
	 * @return
	 * 			el numero de plazas de la estacion utilizadas
	 */
	public int getnParkins_used() {
		return nParkins_used;
	}

	/**
	 * @param nParkins_used el numero de plazas de la estacion utilizadas
	 */
	public void setnParkins_used(int nParkins_used) {
		this.nParkins_used = nParkins_used;
	}

	/**
	 * @return el numero de servicios realizados por la estacion
	 */
	public int getServicesUsed() {
		return servicesUsed;
	}

	/**
	 * @param servicesUsed
	 * 					el numero de servicios realizados por la estacion
	 */
	public void setServicesUsed(int servicesUsed) {
		this.servicesUsed = servicesUsed;
	}

	
}
