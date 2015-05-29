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
	public void addStation(String stationId, long latitude, long longitude, int nParkings) throws EIException;


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
	public void addBicycle(String bicycleId, String model, String stationId) throws EIException;
	
	
	/**
	 * Método que proporciona las bicicletas disponibles de una estación
	 * @return retorna un iterador con les bicicletas de una estación
	 * @throws EIException lanza una excepción en los siguientes casos:
	 * 1.- La estación no existe 
	 * 2.- No hay bicicletas en esta estación
	 */
	public Iterador<Bicycle> bicyclesByStation(String stationId) throws EIException;
	
	
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
	public Bicycle getBicycle(String userId, String stationId, Date dateTime) throws EIException;
	
	
	
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
	public void returnBicycle(String bicycleId, String stationId, Date dateTime) throws EIException;
	
	
	
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
}
