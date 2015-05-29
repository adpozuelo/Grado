package uoc.ei.practica;

import java.util.Date;

import uoc.ei.tads.Cola;
import uoc.ei.tads.ColaConPrioridad;
import uoc.ei.tads.Contenedor;
import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioAVLImpl;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;
import uoc.ei.tads.Lista;
import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.TablaDispersion;

public class BicingManagerImpl implements BicingManager {
	
	/**
	 * vector de estaciones del sistema
	 */
	private Station[] stations;
	private int len;
	
	/**
	 * tabla de dispersion global de bicicletas del sistema
	 */
	private Diccionario<String, Bicycle> bicycles; // para almacenar las estaciones utilizo una tabla de dispersion
	
	/**
	 * AVL de usuarios del sistema
	 */
	private Diccionario<String, User> users; // para almacenar los usuarios utilizo un AVL
	
	/**
	 * cola prioritaria de trabajadores
	 */
	private Cola<Worker> workers;  // para almacenar los trabajadores utilizo una cola con prioridad
	
	/**
	 * AVL de incidencias
	 */
	private Diccionario<Integer, Ticket> tickets; // para almacenar las incidencias utilizo un AVL
	
	/** 
	 * apuntador a la estación más activa
	 */
	private Station mostActiveStation;
	
	/**
	 * apuntador al usuario más activo
	 */
	private User mostActiveUser;
	
	/**
	 * apuntador a la estacion con mas incidencias
	 */
	private Station mostTicketingStation;

	public BicingManagerImpl() {
		this.stations= new Station[S];
		this.len=0;
		this.bicycles= new TablaDispersion<String, Bicycle>(B); // para almacenar las estaciones utilizo una tabla de dispersion
		this.users = new DiccionarioAVLImpl<String, User>(); // para almacenar los usuarios utilizo un AVL
		this.workers = new ColaConPrioridad<Worker>(); // para almacenar los trabajadores utilizo una cola con prioridad
		this.mostActiveStation=null;
		this.mostActiveUser=null;
		this.mostTicketingStation = null; // inicializo la estacion con mas incidencias a NULL
		this.tickets = new DiccionarioAVLImpl<Integer, Ticket>(); // para almacenar las incidencias utilizo un AVL
	}
	
	public void addStation(int stationId, long latitude, long longitude,
			int nParkings) throws EIException {
		Station station = this.getStation(stationId);
		if (station!=null) station.set(latitude, longitude, nParkings);
		else {
			station = new Station(stationId, latitude, longitude, nParkings);
			this.stations[stationId-1]=station;
			if (len<stationId) len=stationId;
		}
	}

	public Station getStation(int stationId) {
		Station ret=null;
		if (stationId<=this.len) 
			ret = this.stations[stationId-1];
		return ret;
	}
	
	public Station getStation(int stationId, String message) throws EIException {
		Station station = this.getStation(stationId);
		if (station==null) throw new EIException(message);
		return station;
	}
	
	public Iterador<Station> stations() throws EIException {
		if (this.len==0) throw new EIException(Messages.NO_STATIONS);
		Iterador<Station> it =  new IteradorVectorImpl(this.stations,this.len,0);
		return it;
	}

	public void addBicycle(String bicycleId, String model, int stationId)
			throws EIException {
		Bicycle bicycle = this.bicycles.consultar(bicycleId); 
		if (bicycle!=null) throw new EIException(Messages.BICYCLE_ALREADY_EXISTS);
		//else
		Station station = this.getStation(stationId, Messages.STATION_NOT_FOUND);
		bicycle = new Bicycle(bicycleId, model);
		station.addBicycle(bicycle);
		bicycle.setCurrentStation(station); // le asigno a la bicicleta la estacion en la que sera aparcada
		this.bicycles.insertar(bicycleId, bicycle);
		
	}

	public Iterador<Bicycle> bicyclesByStation(int stationId)
			throws EIException {
		Station station = this.getStation(stationId, Messages.STATION_NOT_FOUND);
		Contenedor<Bicycle> bicycles = station.bicycles();
		if (bicycles.estaVacio()) throw new EIException(Messages.NO_BICYCLES);
		return bicycles.elementos();
	}

	@Override
	public Iterador<Bicycle> bicycles() throws EIException {
		if (this.bicycles.estaVacio()) throw new EIException(Messages.NO_BICYCLES);
		return bicycles.elementos();
	}
	
	@Override
	public void addUser(String userId, String name) {
		User user = this.users.consultar(userId); // compruebo si el usuario existe en el AVL
		if (user!=null) user.set(name);
		else {
			user = new User(userId, name);
			this.users.insertar(userId, user); // si no existe lo añado al AVL de usuarios
		}
	}

	@Override
	public Iterador<User> users() throws EIException {
		if (this.users.estaVacio()) throw new EIException(Messages.NO_USERS);
		return users.elementos();
	}

	public Bicycle getBicycle(String userId, int fromStationId, Date dateTime)
			throws EIException {
		User user = this.users.consultar(userId); // busco al usuario en el AVL de usuarios
		if (user == null) throw new EIException(Messages.USER_NOT_FOUND); // si el usuario no esta lanzo la excepcion
		Station station = this.getStation(fromStationId, Messages.STATION_NOT_FOUND);
		if (user.hasCurrentService()) throw new EIException(Messages.USER_IS_BUSY);
		Bicycle bicycle = station.getBicycle();
		if (bicycle == null) throw new EIException(Messages.NO_BICYCLES);
		bicycle.startService(user, station, dateTime);
		station.incActivity();
		user.incActicity();
		if (this.mostActiveStation==null || this.mostActiveStation.activity()<station.activity()) this.mostActiveStation=station;
		if (this.mostActiveUser==null || this.mostActiveUser.activity()<user.activity()) this.mostActiveUser=user;
		return bicycle;
	}

	public void returnBicycle(String bicycleId, int toStationId, Date dateTime)
			throws EIException {
		Bicycle bicycle = this.bicycles.consultar(bicycleId); // modificado el numero de parametros para ajustarme al interfaz Diccionario
		if (bicycle == null) throw new EIException(Messages.BICYCLE_NOT_FOUND);	// si la bicicleta no se encuentra lanzo excepcion
		Station station = this.getStation(toStationId, Messages.STATION_NOT_FOUND);
		station.addBicycle(bicycle);
		bicycle.finishService(station, dateTime);
	}

	@Override
	public Iterador<Service> servicesByBicycle(String bicycleId)
			throws EIException {
		Bicycle bicycle = this.bicycles.consultar(bicycleId); // modificado el numero de parametros para ajustarme al interfaz Diccionario
		if (bicycle == null) throw new EIException(Messages.BICYCLE_NOT_FOUND); // si la bicicleta no se encuentra lanzo excepcion
		Contenedor<Service> services= bicycle.services();
		if (services.estaVacio()) throw new EIException(Messages.NO_SERVICES);
		return services.elementos();
	}

	@Override
	public Station mostActiveStation() throws EIException {
		if (this.mostActiveStation==null) throw new EIException(Messages.NO_STATIONS);
		return this.mostActiveStation;
	}

	@Override
	public User mostActiveUser() throws EIException {
		if (this.mostActiveUser==null) throw new EIException(Messages.NO_USERS);
		return this.mostActiveUser;
	}

	@Override
	public void addWorker(String dni, String name) {
		boolean workerExist = false; // control sobre si el trabajador existe ya en la cola
		Iterador<Worker> iw = this.workers.elementos(); // obtengo un iterador a la cola
		while (iw.haySiguiente() && !workerExist) // mientras haya trabajadores y el trabajador no exista en la cola
			workerExist = iw.siguiente().equals(new Worker(dni, name)); // comparo si el trabajador del iterador es igual al trabajador a insertar en la cola
		if (!workerExist) this.workers.encolar(new Worker(dni, name)); // si el trabajador no existe se encola
	}

	@Override
	public Iterador<Worker> workers() throws EIException {
		if (this.workers.estaVacio()) throw new EIException(Messages.NO_WORKERS); // si no hay trabajadores en la cola lanzo la excepcion
		return this.workers.elementos(); // devuelvo un iterador a la cola prioritaria de trabajadores
	}

	@Override
	public int addTicket(String bicicleId, String description, Date dateTime)
			throws EIException {
		Bicycle bicycle = this.bicycles.consultar(bicicleId); // modificado el numero de parametros para ajustarme al interfaz Diccionario
		if (bicycle == null) throw new EIException(Messages.BICYCLE_NOT_FOUND);	// si la bicicleta no se encuentra lanzo excepcion
		Ticket t = new Ticket(bicycle, description, dateTime); // instancio la incidencia
		bicycle.statusToFaulty(); // modifico el estado de la bicicleta a averiada
		this.tickets.insertar(t.getTicketId(), t); // añado la incidencia al AVL
		bicycle.insertarTicket(t); // añado la incidencia a la lista de incidencias de la bicicleta
		Station s = bicycle.getCurrentStation(); // obtengo la estacion en donde esta aparcada la bicicleta
		if (s != null) {  // si la bicicleta esta aparcada en alguna estacion
			s.incTicket(); // aumento el numero de incidencias de la estacion
			s.setBicycleToDamaged(bicycle); // notifico a la estacion que la bicicleta esta averiada para que proceda a su "desactivacion"
			// si le estacion mas problematica existe o si el numero de incidencias de la actual estacion mas problematica es menor que el numero de
			// incidencias de la estacion en donde esta aparcada la bicicleta, situo dicha estacion como estacion mas problematica
			if (this.mostTicketingStation == null || this.mostProblematicStation().getTickets() < s.getTickets()) this.mostTicketingStation = s;
		}
		return t.getTicketId(); // devuelvo el id de la incidencia
	}

	@Override
	public Iterador<Ticket> tickets() throws EIException {
		if (this.tickets.estaVacio()) throw new EIException(Messages.NO_TICKETS); // si no hay incidencias en el AVL lanzo la excepcion
		return this.tickets.elementos(); // devuelvo el iterador a las incidencias del AVL
	}

	@Override
	public Worker assignTicket(int ticketId, Date dateTime) throws EIException {
		Ticket t = this.tickets.consultar(ticketId); // obtengo la incidencia del AVL
		if (t == null) throw new EIException(Messages.TICKET_NOT_FOUND); // si la incidencia no existe lanzo la excepcion
		if (this.workers.estaVacio()) throw new EIException(Messages.NO_WORKERS); // si no hay trabajadores en la cola lanzo la excepcion
		Worker w = this.workers.desencolar(); // obtengo el trabajador que menos incidencias tiene asignadas de la cola
		w.incTickets(); // incremento el numero de incidencias del trabajador
		this.workers.encolar(w); // una vez actualizado el trabajador lo encolo para que se hunda a su nueva posicion
		t.assignToWorker(w, dateTime); // cambio el estado de la incidencia asignandole un trabajador y un fecha de asignacion
		return w;  // devuelvo el trabajador
	}

	@Override
	public void resolveTicket(int ticketId, String desciption, Date dateTime)
			throws EIException {
		Ticket t = this.tickets.consultar(ticketId); // obtengo la incidencia del AVL
		if (t == null) throw new EIException(Messages.TICKET_NOT_FOUND); // si la incidencia no existe lanzo la excepcion
		if (t.getWorker() == null) throw new EIException(Messages.NO_WORKER_ASSIGNED_TO_TICKET); // si la incidencia no tiene trabajador asignado lanzo la excepcion
		t.resolv(desciption, dateTime); // cierro la incidencia
		t.getBicycle().statusToInService(); // modifico el estado de la bicicleta a en servicio
		Station s = t.getBicycle().getCurrentStation(); // obtengo la estacion en donde esta aparcada la bicicleta
		if (s != null) s.setBicycleToInService(t.getBicycle()); // le notifico a la estacion que la bicicleta se encuentra en servicio
		
	}

	@Override
	public Iterador<Ticket> ticketsByWorker(String workerId) throws EIException {
		if (this.tickets.estaVacio()) throw new EIException(Messages.NO_TICKETS); // si no hay incidencias en el AVL lanzo la excepcion
		Lista<Ticket> workerTicketList = new ListaEncadenada<Ticket>(); // lista temporal para devolver el iterador solicitado
		Ticket ticket = null; // ticket donde almacenare cada iteracion
		Iterador<Ticket> itTickets = this.tickets.elementos(); // iterador del AVL de incidencias
		while (itTickets.haySiguiente()) { // mientras existan incidencias en el AVL
			ticket = itTickets.siguiente(); // asigno el objeto al que apunta el iterador a ticket donde almaceno cada iteracion
			// si la incidencia tiene asignada un trabajador y esta en estado asignada y el identificador del trabajador es igual al solicitado en el parametro
			if (ticket.getWorker() != null && ticket.getWorker().getDni().equals(workerId) && ticket.getStatus().equals(Ticket.STATUS_ASSIGNED)) 
				workerTicketList.insertarAlFinal(ticket); // añado la incidencia a la lista temporal
		} 
		// si no hay ninguna incidencia con el criterio solicitado la lista estara vacia, por lo que lanzo la excepcion
		if (workerTicketList.estaVacio()) throw new EIException(Messages.NO_TICKETS);
		return workerTicketList.elementos(); // devuelvo el iterador solicitado
	}

	@Override
	public Iterador<Ticket> ticketsByBicycle(String bicycleId)
			throws EIException {
		Bicycle bicycle = this.bicycles.consultar(bicycleId); // modificado el numero de parametros para ajustarme al interfaz Diccionario
		if (bicycle == null) throw new EIException(Messages.BICYCLE_NOT_FOUND);	// si la bicicleta no se encuentra lanzo excepcion	
		if (bicycle.ticketsEmpty()) throw new EIException(Messages.NO_TICKETS); // si la lista de incidencias de la bicicleta esta vacia lanzo la excepcion
		return bicycle.tickets(); // devuelvo el iterador solicitado
	}

	@Override
	public Station mostProblematicStation() throws EIException {
		if (this.tickets.estaVacio()) throw new EIException(Messages.NO_TICKETS); // si no hay incidencias en el AVL lanzo la excepcion
		return this.mostTicketingStation; // devuelvo la estacion con mas incidencias
	}

	@Override
	public int getNBicycles(int stationId) throws EIException {
		Station station = this.getStation(stationId, Messages.STATION_NOT_FOUND); // si la estacion no existe lanzo la excepcion
		Contenedor<Bicycle> bicycles = station.bicycles(); // obtengo el conjunto de bicicletas aparcadas en la estacion
		Iterador<Bicycle> it = bicycles.elementos(); // obtengo un iterador de dicho conjunto
		int contador = 0; // inicio el contador de bicicletas que cumplen la condicion solicitada a cero
		while (it.haySiguiente()) { // mientras existan elementos en el conjunto de bicicletas aparcadas en la estacion
			if (it.siguiente().getStatus().equals(Bicycle.STATUS_IN_SERVICE)) contador++; // si el estado de la bicicleta es en servicio, incremento el contador
		}
		if (contador == 0) throw new EIException(Messages.NO_BICYCLES);	// si el contador no ha registrado ninguna bicicleta en servicio lanzo la excepcion
		return contador; // devuelvo el numero de bicicletas aparcadas en la estacion que no tienen una incidencia abierta
	}

	@Override
	public int getNParkings(int stationId) throws EIException {
		Station station = this.getStation(stationId, Messages.STATION_NOT_FOUND); // si la estacion no existe lanzo la excepcion
		int i = station.getNFreeParkings(); // obtengo el numero de plazas libres de la estacion
		if (i == 0) throw new EIException(Messages.NO_FREE_PARKINGS); // si no hay plazas libres lanzo la excepcion
		return i; // devuelvo el numero de plazas libres de la estacion
	}

	@Override
	public Station getClosestBike(long latitude, long longitude)
			throws EIException {
		Station nearestStation = null; // estacion mas cercana, si la hay
		double distance = 0; // distancia entre estaciones
		double bestDistance = 0; // mejor distancia encontrada
		Station station = null; // estacion donde almacenare el objeto al que apunta el iterador en cada iteracion
		Iterador<Station> itStation = new IteradorVectorImpl<Station>(this.stations, this.len, 0); // obtengo el iterador
		while (itStation.haySiguiente()) { // mientras existan estaciones
			station = itStation.siguiente(); // la estacion es el objeto al que apunta el iterador
			distance = Math.sqrt(Math.pow((station.getLatitude() - latitude), 2) + Math.pow((station.getLongitude() - longitude), 2)); // aplico la formula entregada con la PRA
			// si la mejor distancia es la inicial o la distancia calculada es menor que la mejor y hay una o mas bicicletas aparcadas en la estacion
			if ((bestDistance == 0 || distance < bestDistance) && !station.bicycles().estaVacio()) { 
				nearestStation = station; // la mejor estacion es la de esta iteracion
				bestDistance = distance; // actualizo la distancia mas corta
			}
		}
		if (nearestStation == null) throw new EIException(Messages.STATION_NOT_FOUND); // si no se ha encontrado ninguna estacion que cumpla el criterio lanzo la excepcion
		return nearestStation; // devuelvo la estación más cercana con una o más bicicletas libres
	}

	@Override
	public Station getClosestParking(long latitude, long longitude)
			throws EIException {
		Station nearestStation = null; // estacion mas cercana, si la hay
		double distance = 0; // distancia entre estaciones
		double bestDistance = 0; // mejor distancia encontrada
		Station station = null; // estacion donde almacenare el objeto al que apunta el iterador en cada iteracion
		Iterador<Station> itStation = new IteradorVectorImpl<Station>(this.stations, this.len, 0); // obtengo el iterador
		while (itStation.haySiguiente()) { // mientras existan estaciones
			station = itStation.siguiente(); // la estacion es el objeto al que apunta el iterador
			distance = Math.sqrt(Math.pow((station.getLatitude() - latitude), 2) + Math.pow((station.getLongitude() - longitude), 2)); // aplico la formula entregada con la PRA
			// si la mejor distancia es la inicial o la distancia calculada es menor que la mejor y la estacion tiene una o mas plazas libres
			if ((bestDistance == 0 || distance < bestDistance) && station.getNFreeParkings() > 0) { 
				nearestStation = station; // la mejor estacion es la de esta iteracion
				bestDistance = distance; // actualizo la distancia mas corta
			}
		}
		if (nearestStation == null) throw new EIException(Messages.STATION_NOT_FOUND); // si no se ha encontrado ninguna estacion que cumpla el criterio lanzo la excepcion
		return nearestStation; // devuelvo la estación más cercana con una o mas plazas libres;
	}
}
