package uoc.ei.practica;

/**
 * clase que representa un trabajador en el sistema
 * @author adpozuelo
 *
 */
public class Worker implements Comparable<Worker> {
	
	/**
	 * dni del trabajdor
	 */
	private String dni;
	
	/**
	 * nombre del trabajador
	 */
	private String name;

	/**
	 * numero de incidencias asignadas al trabajador
	 */
	private int tickets;
	
	/**
	 * Constructor
	 * @param dni
	 * 			dni del trabajador
	 * @param name
	 * 			nombre del trabajador
	 */
	public Worker(String dni, String name) {
		super();
		this.dni = dni;
		this.name = name;
		this.tickets = 0;
	}
	
	/**
	 * devuelve el numero de incidencias asignadas al trabajador
	 * @return the numIncidences
	 */
	public int getTickets() {
		return tickets;
	}
	
	/**
	 * incrementa el numero de incidencias del trabajador
	 */
	public void incTickets() {
		this.tickets++;
	}
	
	/**
	 * compara dos trabajadores segun el numero de incidencias asignadas
	 * @return 
	 * 		-1, 0 o 1 dependiendo del valor de la expresion this.numIncidences - o.getNumIncidences()
	 */
	@Override
	public int compareTo(Worker o) {
		return this.tickets - o.getTickets();
	}
	
	/**
	 * devuelve el dni del trabajador
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * compara si dos trabajadores son iguales segun su dni
	 * @return
	 * 		true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Worker))
			return false;
		else
			return this.dni.equals(((Worker)obj).getDni());
	}

	/**
	 * devuelve la representacion del trabajador en un String
	 */
	@Override
	public String toString() {
		String s = "dni: " + this.dni + "\nname: " + this.name + "\nnumTickets: " + this.tickets;
		return s;
	}

	/**
	 * devuelve el nombre del trabajador
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
