package uoc.ei.practica;

import java.util.Date;

import uoc.ei.practica.adp_tads.VectorADPAcotado;
import uoc.ei.practica.adp_tads.VectorADPAcotadoImpl;
import uoc.ei.practica.adp_tads.VectorADPAcotadoOrdenado;
import uoc.ei.practica.adp_tads.VectorADPAcotadoOrdenadoImpl;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class BicingManagerImpl implements BicingManager {

	private Lista<User> listUsers; // lista de usuarios del TAD BicingManager
	private VectorADPAcotado<Station> listStations; // vector de estaciones del TAD BicingManager
	private VectorADPAcotadoOrdenado<Bicycle> listBicycles; //  vector de bicicletas del TAD BicingManager
	
	private Station mostActiveStation; // estacion mas activa
	private User mostActiveUser; // usuario mas activo

	/**
	 * Default constructor
	 */
	public BicingManagerImpl() {
		super();
		this.listUsers = new ListaEncadenada<User>(); // utilizo una lista encadenada tal y como dice la solucion PEC1
		this.listStations = new VectorADPAcotadoImpl<Station>(S); // utilizo un vector acotado implementado por mi
		this.listBicycles = new VectorADPAcotadoOrdenadoImpl<Bicycle>(B); // utilizo un vector acotado ordenado implementado por mi
		this.mostActiveStation = null; // inicializo la estacion mas activa como nula
		this.mostActiveUser = null; // inicializo el usuario mas activo como nulo
	}

	@Override
	public void addStation(String stationId, long latitude, long longitude,
			int nParkings) throws EIException {
			Station station = station_exist(stationId); //obtengo la estacion del vector
			if (station != null) { // si la estacion existe
				station.setLatitude(latitude); // modifico su latitud
				station.setLongitude(longitude); // modifico su longitud
				station.setnParkings(nParkings); // modifico el numero de plazas
			} else { // en caso contrario, la estacion no existe
				this.listStations.insertar(new Station(station_int_id(stationId), // la añado al vector de estaciones
						latitude, longitude, nParkings), station_int_id(stationId) - 1);
			}
	}

	@Override
	public void addBicycle(String bicycleId, String model, String stationId)
			throws EIException {
		// dado el alto indice de comprobaciones que hay que realizar voy a controlar el flujo
		// de este metodo mediante las siguientes variables booleanas
		// seguire esta metodologia siempre que considere se crea un codigo mas limpio y claro
		boolean exist_bicycle = false; // variable para saber si la bicicleta existe
		boolean exist_station = false; // variable para saber si la estacion existe
		boolean is_station_full = false; // variable para saber si la estacion esta llena
		if (this.listBicycles.obtener(new Bicycle(bicycleId)) != null) { // si la bicicleta existe en el vector ordenado de bicicletas
			exist_bicycle = true; // actualizo la variable booleana
			throw new EIException(Messages.BICYCLE_ALREADY_EXISTS); // lanzo la excepcion notificandolo
		}
		Station station = station_exist(stationId); //obtengo la estacion del vector
		if (station == null) { // si la estacion no existe
					throw new EIException(Messages.STATION_NOT_FOUND); // lanzo la excepcion notificandolo
				} else { // en caso contrario, la estacion existe
					exist_station = true; // actualizo la variable booleana
					if (station.isStationFull()) { // si a la estacion no le quedan plazas de aparcamiento
						is_station_full = true; // actualizo la variable booleana
						throw new EIException(Messages.MAX_NUMBER_OF_BICYCLES); // lanzo la excepcion notificandola			
				}
			}
		// aunque requiere de mas lineas de codigo, he seguido este desarrollo con el objeto de clarificar al maximo
		// el codigo ya que este metodo realiza muchas comprobaciones y compactando el codigo quedaria mucho menos legible.
		// con las variables booleanas compruebo que se cumplen todas las condiciones
		// si la bicicleta no existe y la estacion existe y la estacion no esta llena
		if (!exist_bicycle && exist_station && !is_station_full) {
			Bicycle bicycle = new Bicycle(bicycleId, model); // creo la bicicleta
			this.listBicycles.insertar(bicycle); // añado la bicicleta al vector ordenado de bicicletas del TAD BicingManager
			station.addBicycle(bicycle); // añado la bicicleta a la estacion para que se refleje como aparcada
		}
	}

	@Override
	public void addUser(String userId, String name) {
		boolean encontrado = false; // variable para saber si he encontrado al usuario en la lista
		Posicion<User> pos = null; // posicion dentro del recorrido
		Recorrido<User> rec = listUsers.posiciones(); // recorrido por la lista de usuarios
		while (!encontrado && rec.haySiguiente()) { // mientras no encuentre el usuario y existan usuarios en el recorrido
			pos = rec.siguiente(); // guardo la posicion actual
			// si el id del elemento en dicha posicion es igual al id de usuario que queremos dar de alta en la lista
			if (pos.getElem().getId() == Integer.parseInt(userId)) {
				encontrado = true; // he encontrado el usuario
				pos.getElem().setName(name); // modifico el nombre del usuario
			}
		}
		if (!encontrado) { // si no he encontrado el usuario en la lista
			this.listUsers.insertarAlFinal(new User(Integer.parseInt(userId), // lo añado a la lista
					name));
		}
	}

	@Override
	public void returnBicycle(String bicycleId, String stationId, Date dateTime)
			throws EIException {
		boolean exist_bicycle = false; // variable para saber si la bicicleta existe
		boolean exist_station = false; // variable para saber si la estacion existe
		boolean is_station_full = false; // variable para saber si la estacion no esta llena
		Bicycle bicycle = this.listBicycles.obtener(new Bicycle(bicycleId)); // compruebo si la bicicleta existe
		if (bicycle != null) // si la bicicleta existe
			exist_bicycle = true; // marco la variable
		else // en caso contrario, la bicicleta no existe
			throw new EIException(Messages.BICYCLE_NOT_FOUND); // lanzo la excepcion
		Station station = station_exist(stationId);  // compruebo si la estacion existe
		if (station != null) // si la estacion existe
			exist_station = true; // marco la variable 
		else // en caso contrario, la estacion no existe
			throw new EIException(Messages.STATION_NOT_FOUND); // lanzo la excepcion
		if (exist_station && station.isStationFull()) { // si la estacion existe y esta llena
			is_station_full = true;  // marco la variable
			throw new EIException(Messages.MAX_NUMBER_OF_BICYCLES); // lanzo la excepcion
		}
		// si existe la bicicleta y existe la estacion y la estacion no esta llena
		if (exist_bicycle && exist_station && !is_station_full) {
			if (bicycle.getActiveService() != null) { // si la bicicleta tiene servicio en activo
				User user = bicycle.getActiveService().getUsuario(); // adquiero el usuario del servicio activo de la bicicleta
				user.disetAssignedBicycle(); // a dicho usuario le desasigno la bicicleta
				bicycle.deactivateService(dateTime, station); // cierro el servicio de la bicicleta con la fecha y estacion de entrega
				station.addBicycle(bicycle); // retorno la bicicleta a la estacion destino
			} else { // en caso contrario, la bicicleta no tiene servicio activo
				throw new EIException(Messages.NO_SERVICES); // lanzo la excepcion
			}
		}
	}

	@Override
	public Iterador<Station> stations() throws EIException {
		if (this.listStations.estaVacio()) { // si el vector de estaciones esta vacio
			throw new EIException(Messages.NO_STATIONS); // lanzo excepcion notificandolo
		} else { // en caso contrario, no esta vacio
			return this.listStations.elementos(); // devuelvo el iterador
		}
	}

	@Override
	public Iterador<Bicycle> bicyclesByStation(String stationId)
			throws EIException {
		boolean exist_station = false; // variable para saber si la estacion existe
		boolean is_station_empty = false; // variable para saber si la estacion esta vacia
		Station station = station_exist(stationId); //obtengo la estacion del vector
		if (station == null) { // si la estacion no existe
					throw new EIException(Messages.STATION_NOT_FOUND); // lanzo la excepcion notificandolo
				} else { // en caso contrario, la estacion existe
					exist_station = true; // actualizo la variable booleana
				}
		if (exist_station && station.getnParkins_used() == 0) { // si a la estacion existe y no tiene bicicletas
			is_station_empty = true; // actualizo la variable booleana
			throw new EIException(Messages.NO_BICYCLES); // lanzo la excepcion notificandola			
		}
		// Si la estacion existe y no esta vacia
		if (exist_station && !is_station_empty)
			return station.bicyclesParked(); // devuelvo el iterador
		else // en caso contrario
			return null; // devuelvo null
	}

	@Override
	public Iterador<Bicycle> bicycles() throws EIException {
		if (this.listBicycles.estaVacio()) { // si el vector de bicicletas esta vacio
			throw new EIException(Messages.NO_BICYCLES); // lanzo excepcion
		} else { // en caso contrario, el vector no esta vacio
			return this.listBicycles.elementos(); // devuelvo el iterador
		}
	}

	@Override
	public Iterador<User> users() throws EIException {
		if (this.listUsers.estaVacio()) { // si la lista de usuarios esta vacia
			throw new EIException(Messages.NO_USERS); // lanzo excepcion
		} else { // en caso contrario, la lista no esta vacia
			return this.listUsers.elementos(); // devuelvo el iterador.
		}
	}

	@Override
	public Bicycle getBicycle(String userId, String stationId, Date dateTime)
			throws EIException {
		boolean exist_user = false; // variable para saber si he encontrado al usuario en la lista
		boolean is_user_busy = false; // variable para saber si el usuario ya tiene una bicicleta asignada
		boolean exist_station = false; // variable para saber si la estacion existe
		boolean is_station_empty = false; // variable para saber si la estacion esta vacia
		User user = user_exist(userId); // compruebo si el usuario existe
		if (user == null) // si no existe
			throw new EIException(Messages.USER_NOT_FOUND); // lanzo la excepcion
		else // en caso contrario, el usuario existe
			exist_user = true; // actualizo la variable booleana
		if (exist_user && user.getAssignedBicycle() != null) { // si el usuario existe y tiene una bicicleta asignada
			is_user_busy = true; // lo marco en la variable booleana
			throw new EIException(Messages.USER_IS_BUSY); // lanzo la excepcion
		}
		Station station = station_exist(stationId); //obtengo la estacion del vector
		if (station == null) { // si la estacion no existe
					throw new EIException(Messages.STATION_NOT_FOUND); // lanzo la excepcion notificandolo
				} else { // en caso contrario, la estacion existe
					exist_station = true; // actualizo la variable booleana
				}
		if (exist_station && station.getnParkins_used() == 0) { // si a la estacion existe y no tiene bicicletas
			is_station_empty = true; // actualizo la variable booleana
			throw new EIException(Messages.NO_BICYCLES); // lanzo la excepcion notificandola			
		}
		// si el usuario existe y la estacion existe y la estacion no esta vacia y el usuario no tiene asignada una bicicleta
		if (exist_user && exist_station && !is_station_empty && !is_user_busy) {
			Service service = new Service(user, station, dateTime); // creo el servicio
			Bicycle bicycle = station.getLessUsedBicycle(); // obtengo la bicicleta menos utilizada de la estacion
			bicycle.activateService(service); // activo el servicio en la bicicleta
			user.setAssignedBicycle(bicycle); // asigno la bicicleta al usuario
			// si el usuario mas activo es nulo (primera vez) o si el usuario tiene mas servicios que el actual usuario con mas servicios
			if (this.mostActiveUser == null || user.getnServices() > this.mostActiveUser.getnServices())
				this.mostActiveUser = user; // cambio el usuario con mas servicios
			// si la estacion mas activa es nulo (primera vez) o si la estacion tiene mas servicios que la estacion actual con mas servicios
			if (this.mostActiveStation == null || station.getServicesUsed() > this.mostActiveStation.getServicesUsed())
				this.mostActiveStation = station;
			return bicycle; // devuelvo la bicicleta
		} else { // en caso contrario, no se cumple alguna de las condiciones
			return null; // devuelvo null
		}
	}

	@Override
	public Iterador<Service> servicesByBicycle(String bicycleId)
			throws EIException {
		boolean exist_bicycle = false; // variable booleana para saber si existe la bicicleta
		boolean bicycle_services_empty = false; // variable boolena para saber si la lista de servicios de la bicicleta esta vacia
		Bicycle bicycle = this.listBicycles.obtener(new Bicycle(bicycleId)); // compruebo si la bicicleta existe
		if (bicycle != null) // si la bicicleta existe
			exist_bicycle = true; // marco la variable
		else // en caso contrario, la bicicleta no existe
			throw new EIException(Messages.BICYCLE_NOT_FOUND); // lanzo la excepcion
		if (exist_bicycle && bicycle.getN_services() == 0) { // si la bicicleta existe y la lista de servicios esta vacia
			bicycle_services_empty = true; // marco la variable
			throw new EIException(Messages.NO_SERVICES); // lanzo la excepcion
		}
		// si existe la bicicleta y la lista de servicios no esta vacia
		if (exist_bicycle && !bicycle_services_empty)
			return bicycle.servicesByBicyclete(); // devuelvo el iterador
		else // en caso contrario
			return null; // devuelvo null
	}

	@Override
	public Station mostActiveStation() throws EIException {
		if (this.mostActiveStation == null) { // si no existe una estacion mas activa
			throw new EIException(Messages.NO_STATIONS); // lanzo la excepcion
		} else { // en caso contrario
			return this.mostActiveStation; // devuelvo la estacion
		}
	}

	@Override
	public User mostActiveUser() throws EIException {
		if (this.mostActiveUser == null) { // si no existe un usuario mas activo
			throw new EIException(Messages.NO_USERS); // lanzo la excepcion
		} else { // en caso contrario
			return this.mostActiveUser; // devuelvo el usuario
		}
	}

	/**
	 * encuentra una estacion dentro del vector de estaciones mediante el string stationId
	 * @param stationId
	 * 					el identificador de la estacion
	 * @return
	 * 			la estacion si existe, null en caso contrario
	 * @throws EIException 
	 * 			en caso de que la entrada no cumpla el formato STATIONx, donde x es el numero de estacion
	 */
	private Station station_exist(String stationId) throws EIException {
		boolean is_correct_format = false; // variable booleana para controlar si la entrada cumple el formato establecido
		try {
			String stationFormat = stationId.substring(0, 7);
			is_correct_format = stationFormat.equalsIgnoreCase(new String("STATION"));
		}
		catch (Exception e) {
			throw new EIException("Invalid station id");
		}
		if (!is_correct_format) { // si el formato no es correcto
			// esta comprobacion es necesaria dado que los parametros stationId en el fichero in1.txt cumplen el 
			// formato STATIONx donde x es el numero de estacion. Si no cumple este formato se considera estacion no valida
			return null; // devuelvo una estacion nula (no encontrada). Se podria lanzar una excepcion por formato invalido
		} else { // en caso contrario, el string cumple el formato requerido para este juego de pruebas
			int stationIdCorrectInt = station_int_id(stationId); // convierto el string stationId a entero
			if (stationIdCorrectInt <= 0 // si el identificador de estacion esta fuera del rango permitido, entre 1 y el tamaño de la lista de estaciones
					&& stationIdCorrectInt > this.listStations.longitud()) {
				return null; // devuelvo una estacion nula (no encontrada). Se podria lanzar una excepcion por rango invalido
			} else { // en caso contrario, el entero que obtengo esta dentro del rango permitido
				return this.listStations.obtener(stationIdCorrectInt - 1); //devuelvo la estacion de la posicion dentro del vector
			}
		}
	}
	
	/**
	 * convierte el string stationId en un entero que representa el id de la estacion
	 * @param stationId
	 * 					el identificador de la estacion
	 * @return
	 * 			el entero que representa el id de la estacion
	 * @throws EIException 
	 */
	private int station_int_id(String stationId) throws EIException {
		String stationIdSplit = stationId.substring(7); // elimino los 7 primeros caracteres del STATION_ID
		int id = -1; // identificador no valido
		try {
			id = Integer.parseInt(stationIdSplit.trim()); // parseo el string restante a entero
		} // si ocurre algun error, salta la excepcion
		catch (Exception e) {
			throw new EIException("Invalid station id");
		}
		return id; // si no ha habido ningun error devuelvo el id valido, en caso contrario un id fuera de rango valido
	}
	
	/**
	 * encuentra un usuario dentro de la lista de usuarios
	 * @param userId
	 * 				el identificador del usuario
	 * @return
	 * 		el usuario si lo encuentra, null en caso contrario
	 * @throws EIException
	 * 					si el string identificador de usuario no es valido
	 */
	private User user_exist(String userId) throws EIException {
		boolean encontrado = false; // variable para saber si hemos encontrado al usuario
		int userIdInt = -1; // control de los identificadores de usuarios validos
		User user = null; // usuario temporal para almacenar el objeto al que apunta el iterador
		Iterador<User> it = listUsers.elementos(); // iterador por la lista de usuarios
		while (!encontrado && it.haySiguiente()) { // mientras no encuentre el usuario y existan usuarios en la lista
			user = it.siguiente(); // guardo el usuario actual
			try { // compruebo que convertir el String a int se haga correctamente mediate un try-catch recogiendo cualquier excepcion lanzada
				userIdInt = Integer.parseInt(userId);
			}
			catch (Exception e) { // y manejandola relanzando una de nuestras excepciones teniendo la salida deseada
				throw new EIException("Invalid user id");
			}	
			if (user.getId() == userIdInt) { // si el id del user es igual al que tenemos como parametro
				encontrado = true; // he encontrado al usuario
			}
		}
		if (encontrado) // si he encontrado al usuario
			return user; // lo devuelvo
		else // en caso contrario
			return null; // devuelvo null
	}
}
