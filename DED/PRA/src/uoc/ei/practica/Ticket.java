package uoc.ei.practica;

import java.util.Date;

/**
 * clase que representa una incidencia en el sistema
 * @author adpozuelo
 *
 */
public class Ticket implements Comparable<Ticket> {
	
	// estados posibles de una incidencia
	public static final String STATUS_NOT_ASSIGNED = "NOT_ASSIGNED";
	public static final String STATUS_ASSIGNED = "ASSIGNED";
	public static final String STATUS_SOLVED = "SOLVED";
	
	/**
	 * controlador de los indices de los ids de las incidencias
	 */
	private static int ticketsIdsControler = 0;
	
	/**
	 * identificador de la incidencia
	 */
	private int ticketId;
	
	/**
	 * descripcion de la incidencia cuando se abre
	 */
	private String description_open;
	
	/**
	 * descripcion de la incidencia cuando se cierra
	 */
	private String description_close;
	
	/**
	 * fecha de apertura de la incidencia
	 */
	private Date date_initial;
	
	/**
	 * fecha de asignacion de la incidencia
	 */
	private Date date_assign;
	
	/**
	 * fecha de resolucion de la incidencia
	 */
	private Date date_resolv;
	
	/**
	 * estado de la incidencia
	 */
	private String status;
	
	/**
	 * bicicleta sobre la que se ha abierto la incidencia
	 */
	private Bicycle bicycle;
	
	/**
	 * trabajador al que se le asigna la incidencia
	 */
	private Worker worker;
	
	/**
	 * ultima fecha añadidad a la incidencia
	 */
	private Date dateTime;
	
	/**
	 * Constructor
	 * @param bicycleId
	 * @param description_open
	 * @param date_initial
	 */
	public Ticket(Bicycle bicycleId, String description_open, Date date_initial) {
		super();
		this.ticketId = ++this.ticketsIdsControler; // actualizo el identificador de incidencias y luego lo asigno
		this.bicycle = bicycleId;
		this.description_open = description_open;
		this.description_close = null;
		this.date_initial = date_initial;
		this.dateTime = date_initial;
		this.date_assign = null;
		this.date_resolv = null;
		this.status = STATUS_NOT_ASSIGNED;
		this.worker = null;
	}
	
	/**
	 * metodo que cierra una incidencia porque ya esta resuelta
	 * @param desc
	 * 			descripcion
	 * @param date
	 * 			fecha de resolucion de la incidencia
	 */
	public void resolv(String desc, Date date) {
		this.description_close = desc;
		this.date_resolv = date;
		this.dateTime = date;
		this.status = STATUS_SOLVED;
	}
	
	/**
	 * metodo que asigna un trabajador a la incidencia
	 * @param worker
	 * 			el trabajador asignado
	 * @param date
	 * 			fecha de asignacion de la incidencia
	 */
	public void assignToWorker(Worker worker, Date date) {
		this.date_assign = date;
		this.dateTime = date;
		this.worker = worker;
		this.status = STATUS_ASSIGNED;
	}
	
	/**
	 * devuelve el identificador de la incidencia
	 * @return
	 * 		el identificador
	 */
	public int getTicketId() {
		return this.ticketId;
	}

	/**
	 * compara si dos incidencias son iguales segun su id
	 * @return
	 * 		true si las dos incidencias son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ticket))
			return false;
		else
			return Integer.valueOf(this.ticketId).equals(((Ticket)obj).getTicketId());
	}

	/**
	 * compara si dos incidencias iguales segun si id
	 * @return
	 * 		-1, 0 o 1 dependiendo del valor de la expresion this.ticketId - o.getTicketId();
	 */
	@Override
	public int compareTo(Ticket o) {
		return this.ticketId - o.getTicketId();
	}

	/**
	 * devuelve la representacion de la incidencia en un String
	 */
	@Override
	public String toString() {
		String s = "id: " + this.ticketId + "\ndescription: " + ((this.description_close == null) ? this.description_open : this.description_close) + "\nbicycle: " + this.bicycle.getIdentifier() + "\nstatus: " + this.status
				+ "\ndateTime: " + DateUtils.format(this.dateTime) + ((this.worker != null && !this.status.equals(STATUS_SOLVED)) ? ("\nworker: " + this.worker.getName()) : "" ) + "\n";
		return s;
	}

	/**
	 * devuelve el trabajador asignado a la incidencia
	 * @return the worker
	 */
	public Worker getWorker() {
		return worker;
	}

	/**
	 * devuelve la bicicleta asignada a la incidencia
	 * @return the bicycle
	 */
	public Bicycle getBicycle() {
		return bicycle;
	}

	/**
	 * devuelve el estado de la incidencia
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
}
