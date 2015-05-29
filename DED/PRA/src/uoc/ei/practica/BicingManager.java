package uoc.ei.practica;

import java.util.Date;

import uoc.ei.tads.Iterador;

/** 
 * Definición del TAD de gestión del servicio de bicing
 */
public interface BicingManager {

	
	public static final int S = 20;
	
	public static final int B = 150;
	
	/**
	 * Método que añade una estación en el sistema de gestión de bicicletas.
	 * En caso que la estación ya exista se modificarán sus datos: latitude, longitude, nParkings
	 * @param stationId identificador de la estación
	 * @param latitude posición GPS
	 * @param longitude posición GPS
	 * @param nParkings número de bicicletas que puede gestionar la estación
	 */
	public void addStation(int stationId, long latitude, long longitude, int nParkings) throws EIException;


	/**
	 * método que proporciona un iterador con las estaciones del sistema
	 * @return retorna el iterador
	 * @throws EIException Lanza una excepción en el caso que no exista ningún elemento
	 */
	public Iterador<Station> stations() throws EIException;
	

	
	/**
	 * método que añade una bicicleta asociada a una estación
	 * @param bicycleId identificador único de una bicicleta
	 * @param model modelo de la bicicleta
	 * @param stationId identificador de la estación donde estará ubicada la bicicleta
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- Si la bicicleta ya existe en el sistema.
	 * 2.- La estación no existe
	 * 3.- Se supera el máximo número de bicicletes sobre una estación
	 */
	public void addBicycle(String bicycleId, String model, int stationId) throws EIException;
	
	
	/**
	 * Método que proporciona las bicicletas disponibles de una estación
	 * @return retorna un iterador con les bicicletas de una estación
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La estación no existe 
	 * 2.- No hay bicicletas en esta estación
	 */
	public Iterador<Bicycle> bicyclesByStation(int stationId) throws EIException;
	
	
	/**
	 * método que proporciona todas las bicicletas del sistema.
	 * @return retorna un iterador con todas las bicicletas del sistema
	 * @throws EIException lanza una excepción en el caso que el iterador no tenga ningún elemento.
	 */
	public Iterador<Bicycle> bicycles() throws EIException;
	
	/**
	 * método que añade un usuario en el sistema. En caso que el usuario ya existe modifica sus datos.
	 * @param userId identificador del usuario
	 * @param name nombre del usuario
	 */
	public void addUser(String userId, String name);
	
	/**
	 * método que proporciona todos los usuarios del sistema.
	 * @return retorna un iterador con todos los usuarios del sistema
	 * @throws EIException lanza una excepción en el caso que el iterador no tenga ningún elemento
	 */
	public Iterador<User> users() throws EIException;
	
	
	/**
	 * Método que permite coger una bicicleta disponible de una estación.
	 * De entre todas las bicicletas (disponibles) de una estación
	 * se proporciona la que se haya utilizado "menos tiempo"
	 * @param userId identificador del usuario que solicita una bicicleta
	 * @param stationId identificador de la estación de bicicletas
	 * @param dateTime fecha en la que se obtiene la bicicleta
	 * @return retorna una bicicleta que se haya utilizado menos. 
	 * En caso que hayan varias bicicletas con el mismo tiempo de utilización
	 * se proporcionará cualquiera de ellas. 
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- El usuario no existe
	 * 2.- La estación no existe
	 * 3.- El usuario ya dispone de una bicicleta
	 * 4.- No hay ninguna bicicleta disponible
	 */
	public Bicycle getBicycle(String userId, int stationId, Date dateTime) throws EIException;
	
	
	
	/**
	 * Método que permite retornar una bicicleta que previamente ha obtenido
	 * @see #getBicycle  
	 * @param bicycleId identificador de la bicicleta a retornar
	 * @param stationId identificador de la estación sobre la que es quiere retornar la bicicleta
	 * @param dateTime fecha
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La bicicleta no exista
	 * 2.- La estación no exista
	 * 3.- La estación está completa y no tiene espacios libres.
	 */
	public void returnBicycle(String bicycleId, int stationId, Date dateTime) throws EIException;
	
	
	
	/**
	 * Método que proporciona los servicios que ha realizado una determinada bicicleta
	 * @param bicycleId identificador de la bicicleta
	 * @return retorna los servicios
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La bicicleta no exista
	 * 2.- La bicicleta no haya hecho ningún servicio
	 */
	public Iterador<Service> servicesByBicycle(String bicycleId) throws EIException;
	
	
	/**
	 * Método que proporciona la estación más activa
	 * @return la estación más activa
	 * @throws EIException lanza una excepción en el caso que no exista ninguna estación con actividad.
	 */
	public Station mostActiveStation() throws EIException;
	
	/**
	 * método que proporciona el usuario más activo
	 * @return el usuari más activo
	 * @throws EIException lanza una excepción en el caso que no haya ningún usuario con actividad
	 */
	public User mostActiveUser() throws EIException;
	
	// NEW OPERARTIONS IN PRAC
	
	/**
	 * Mètode que añade un nuevo operario al sistema de gestión de bicicletas. 
	 * De cada operario sabemos su código identificador (string con el DNI) y su nombre
	 * @param dni DNI del operario
	 * @param name Nombre del operario
	 */
	public void addWorker(String dni, String name);
	
	/**
	 * Método que proporciona un iterador con los operarios del sistema.
	 * @return devuelve el iterador
	 * @throws EIException Lanza una excepción en el caso que no exista ningún elemento
	 */
	public Iterador<Worker> workers() throws EIException;
	
	/**
	 * Registra una nueva incidencia de una bicicleta.
	 * La incidencia estará en estado pendiente de asignar.
	 * @param bicicleId identificador de la bicicleta
	 * @param description descripción de la averia
	 * @param dateTime fecha/hora de la averia
	 * @return l'identificador de la averia
	 * @throws EIException Lanza una excepción en el caso que no exista la bicicleta
	 */
	public int addTicket(String bicicleId, String description, Date dateTime) throws EIException;
	
	/**
	 * Método que proporciona un iterador con las incidencias del sistema.
	 * @return devuelve el iterador
	 * @throws EIException Lanza una excepción en el caso que no exista ningún elemento
	 */
	public Iterador<Ticket> tickets() throws EIException;
	
	/**
	 * Asigna una incidencia a un operario.
	 * Se asignará el operario con menos incidencias asignadas, y la incidencia pasará a estado asignada.
	 * @param ticketId identificador de la incidencia
	 * @param dateTime fecha/hora de la asignación
	 * @return operario assignado a la incidencia
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La incidencia no existe
	 * 2.- No hay operarios
	 */
	public Worker assignTicket(int ticketId, Date dateTime) throws EIException;
		
	
	/**
	 * Resuelve una incidencia. Recibiremos el identificador de la incidencia, una explicación de la reparación y la fecha/hora. La incidencia pasará  a estado resuelto.
	 * @param ticketId identificador de la incidencia
	 * @param desciption explicación de la reparación
	 * @param dateTime fecha/hora de la reparación
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La incidencia no existe
	 * 2.- La incidencia no tiene operario asignado
	 */
	public void resolveTicket(int ticketId, String desciption, Date dateTime) throws EIException;
	
	/**
	 * Método que proporciona un iterador con las incidencias del operario.
	 * @param workerId identificador del operario
	 * @return devuelve el iterador
	 * @throws EIException Lanza una excepción si el operario no tiene incidencias asignadas
	 */
	public Iterador<Ticket> ticketsByWorker(String workerId) throws EIException;
	
	/**
	 * Método que proporciona un iterador con las incidencias de la bicicleta.
	 * @param bicicleId identificador de la bicicleta
	 * @return evuelve el iterador
	 * @throws EIException EIException lanza una excepción en los siguientes casos:
	 * 1.- La bicicleta no existe
	 * 2.- La bicicleta no tiene incidencias asignadas
	 */
	public Iterador<Ticket> ticketsByBicycle(String bicycleId) throws EIException;
	
	/**
	 * Consultar la estación más conflictiva (la que ha registrado más incidencias).
	 * @return La estación más conflictiva
	 * @throws EIException Lanza una excepción si no se ha registrado todavia ninguan incidencia
	 */
	public Station mostProblematicStation() throws EIException;
	
	/**
	 * Consultar el número de bicicletas aparcadas en una estación (que no tenga una incidencia abierta).
	 * @param stationId identificador de la estación
	 * @return el número de bicicletas aparcadas en una estación (que no tenga una incidencia abierta). 
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La estación no existe
	 * 2.- La estación no tiene bicicletas aparcadas (que no tenga una incidencia abierta). 
	 */
	public int getNBicycles(int stationId) throws EIException;
	
	/**
	 * Consulta el número de plazas libres en una estación.
	 * @param stationId identificador de la estación
	 * @return el número de plazas libres en una estación.
	 * @throws lanza una excepción en los siguientes casos:
	 * 1.- La estación no existe
	 * 2.- La estación no tiene plazas libres. 
	 */
	public int getNParkings(int stationId) throws EIException;
	
	/**
	 * Consultar la estación más cercana con una o más bicicletas libres, a partir de las coordenadas (latitud, longitud).
	 * @param latitude posición GPS
	 * @param longitude posición GPS
	 * @return la estación más cercana con una o más bicicletas libres
	 * @throws EIException Lanza una excepción si ho hauy ninguna estación con bicicletas libres
	 */
	public Station getClosestBike(long latitude, long longitude) throws EIException;
	
	/**
	 * Consultar la estación más cercana con una o más plazas libres, a partir de las coordenadas (latitud, longitud). 
	 * @param latitude posición GPS
	 * @param longitude posición GPS
	 * @return la estación más cercana con una o más plazas libres
	 * @throws EIException lLanza una excepción si ho hauy ninguna estación con plazas libres
	 */
	public Station getClosestParking(long latitude, long longitude) throws EIException;
}
