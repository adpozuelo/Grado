package uoc.ei.practica;

import java.util.Comparator;

import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;

/**
 * Método que representa una estación del sistema
 *
 */
public class Station {

	/**
	 * Comparador de las bicicletas de una estación basado en el tiempo de uso
	 */
	private static final Comparator BICYCLE_COMPARATOR = new Comparator<Bicycle>(){

		@Override
		public int compare(Bicycle bicycle1, Bicycle bicycle2) {
			return (int)(bicycle1.time()-bicycle2.time());
		}
		
	};
	
	/**
	 * identificador de la estación
	 */
	private int stationId;
	
	/**
	 * coordenada GPS
	 */
	private long latitude;

	/**
	 * coordenada GPS
	 */
	private long longitude;
	
	/**
	 * lista de les bicicletes de una estación
	 */
	private Lista<Bicycle> bicycles;
	
	/**
	 * lista de las bicicletas averiadas de la estacion
	 */
	private Lista<Bicycle> damagedBicycles;
	
	/**
	 * número de plazas de parking
	 */
	private int nParkings;
	
	/**
	 * actividad de la estación
	 */
	private int activity;
	
	/**
	 * numero de incidencias de la estacion
	 */
	private int tickets;


	public Station(int stationId, long latitude, long longitude, int nParkings) {
		this.stationId=stationId;
		this.set(latitude, longitude, nParkings);
		this.bicycles=new ListaEncadenada<Bicycle>(); // lista de las bicicletas aparcadas de la estacion
		this.damagedBicycles = new ListaEncadenada<Bicycle>(); // lista de las bicicletas averiadas de la estacion
		this.tickets = 0; // numero de incidencias de la estacion
	}

	public void set(long latitude, long longitude, int nParkings) {
		this.latitude = latitude;
		this.longitude= longitude;
		this.nParkings=nParkings; 
	}
	
	/**
	 * metodo que desactiva una bicicleta de la lista de bicicletas aparcadas cuando dicha bicicleta esta averiada
	 * @param bicycle
	 * 			la bicicleta a mover de la lista de aparcadas a la lista de averiadas
	 */
	public void setBicycleToDamaged(Bicycle bicycle) {
		Posicion<Bicycle> pos = null; // posicion dentro del recorrido
		Recorrido<Bicycle> rec = this.bicycles.posiciones(); // recorrido por la lista de bicicletas
		while (rec.haySiguiente()) { // mientras existan bicicletas en el recorrido
			pos = rec.siguiente(); // pasamos a la siguiente
			if (pos.getElem().equals(bicycle)) { // si el elemento de la posicion es igual a la bicicleta a desactivar
				this.damagedBicycles.insertarAlFinal(bicycle); // la añado a la lista de bicicletas averiadas
				this.bicycles.borrar(pos); // la eliminio de la lista de bicicletas disponibles
			}
		}
	}

	/**
	 * metodo que activa una bicicleta de la lista de bicicletas aparcadas cuando dicha bicicleta vuelve al servicio
	 * @param bicycle
	 * 			la bicicleta a mover de la lista de aparcadas a la lista de averiadas
	 */
	public void setBicycleToInService(Bicycle bicycle) {
		Posicion<Bicycle> pos = null; // posicion dentro del recorrido
		Recorrido<Bicycle> rec = this.damagedBicycles.posiciones(); // recorrido por la lista de bicicletas
		while (rec.haySiguiente()) { // mientras existan bicicletas en el recorrido
			pos = rec.siguiente(); // pasamos a la siguiente
			if (pos.getElem().equals(bicycle)) { // si el elemento de la posicion es igual a la bicicleta a activar
				this.bicycles.insertarAlFinal(bicycle); // la añado a la lista de bicicletas aparcadas
				this.damagedBicycles.borrar(pos); // la eliminio de la lista de bicicletas averiadas
			}
		}
	}
	
	/**
	 * método que añade una bicicleta en una estación
	 * @param bicycle
	 * @throws EIException
	 */
	public void addBicycle(Bicycle bicycle) throws EIException {
		if (this.bicycles.numElems()>=this.nParkings) throw new EIException(Messages.MAX_NUMBER_OF_BICYCLES);
		else this.bicycles.insertarAlFinal(bicycle);
	}

	/** 
	 * método que proporciona las bicicletas de la estación
	 * @return
	 */
	public Contenedor<Bicycle> bicycles() {
		return this.bicycles;
	}
	
	
	/**
	 * método que proporciona una bicicleta disponible. Esta bicicleta será la que
	 * se haya utilizado menos
	 * @return
	 */
	public Bicycle getBicycle() {
		Bicycle bicycle=null; // bicicleta a devolver
		if (!this.bicycles.estaVacio()) { // si la lista de bicicletas aparcadas no esta vacia
			Posicion<Bicycle> posLessUsed = null; // posicion de la bicicleta menos utilizada
			Posicion<Bicycle> pos = null; // posicion dentro del recorrido
			Recorrido<Bicycle> rec = this.bicycles.posiciones(); // recorrido por la lista de usuarios
			while (rec.haySiguiente()) { // mientras existan bicicletas en el recorrido
				pos = rec.siguiente(); // pasamos a la siguiente
				//si la bicicleta menos utiliza es nula o si el tiempo de dicha bicicleta es mayor que la de la actual iteracion
				if (posLessUsed == null || posLessUsed.getElem().getTotalTime() > pos.getElem().getTotalTime()) {
					posLessUsed = pos; // asigno a la bicicleta menos utilizada la bicicleta de la iteracion
				}
			}
			bicycle = posLessUsed.getElem(); // obtengo la bicicleta menos utilizada
			this.bicycles.borrar(posLessUsed); // borro la posicion de la bicicleta menos utilizada de la lista
		}
		return bicycle; // devuelvo la bicicleta con menos tiempo de uso o null si no hay bicicletas disponibles
	}

	/**
	 * método que proporciona una representación en forma de String de una estación
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer("id: "+this.stationId).append(Messages.LS);
		sb.append("latitude: ").append(this.latitude).append(Messages.LS);
		sb.append("longitude: ").append(this.longitude).append(Messages.LS);
		sb.append("nParkings: ").append(this.nParkings).append(Messages.LS);
		sb.append("bicycles: ").append(this.bicycles.numElems()).append(Messages.LS);
		
		return sb.toString();
		
	}

	/**
	 * método que proporciona el identificador de la estación
	 * @return identificador de la estación
	 */
	public int getIdentifier() {
		return this.stationId;
	}
	
	/**
	 * método que incrementa la actividad de una estación
	 */
	public void incActivity() {
		this.activity++;
	}

	/**
	 * método que proporciona la actividad de una estación
	 * @return actividad de una estación
	 */
	public int activity() {
		return this.activity;
	}
	
	/**
	 * método que incrementa las incidencias de una estación
	 */
	public void incTicket() {
		this.tickets++;
	}

	/**
	 * devuelve el numero de incidencias de una estacion
	 * @return the tickets
	 */
	public int getTickets() {
		return this.tickets;
	}
	
	/**
	 * devuelve el numero de plazas libres de la estacion
	 * @return
	 */
	public int getNFreeParkings() {
		return this.nParkings - this.bicycles.numElems();
	}

	/**
	 * @return the latitude
	 */
	public long getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public long getLongitude() {
		return longitude;
	}
}