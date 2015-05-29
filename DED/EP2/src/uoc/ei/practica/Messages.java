package uoc.ei.practica;

/** 
 * Interfaz que define los mensajes 
 * @author   Equip docent de Disseny d'Estructura de Dades de la UOC
 * @version  Otoño 2013
 */
public interface Messages {
	
	public static final String LS = System.getProperty("line.separator");
	public static final String PREFIX = "\t";
	
	public static final String NO_STATIONS = "There are no stations";
	public static final String STATION_NOT_FOUND = "Station not found";
	
	public static final String BICYCLE_NOT_FOUND = "Bicycle not found";
	public static final String BICYCLE_ALREADY_EXISTS = "Bicycle already exists";
	public static final String NO_BICYCLES = "There are no bicycles";
	public static final String MAX_NUMBER_OF_BICYCLES = "Maximun number of bicycles";

	public static final String USER_NOT_FOUND = "User not found";
	public static final String NO_USERS = "There are no users";
	public static final String USER_IS_BUSY = "User is busy";

	public static final String NO_SERVICES = "There are no services";
	public static final Object RUNNING = "Running";
	public static final Object PARKING = "Parking";
	
	
	 
}
