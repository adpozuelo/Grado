package uoc.ei.practica;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class User {

	private int id; // el identificador del usuario
	private String name; // el nombre del usuario
	private Bicycle assignedBicycle; // la bicicleta asignada al usuario
	private int nServices; // el numero de servicios que tiene el usuario

	/**
	 * Constructor
	 * 
	 * @param id
	 *            el identificador del usuario
	 * @param name
	 *            el nombre del usuario
	 * @throws EIException
	 *             en caso de que el id no sea un numero natural
	 */
	public User(int id, String name) {
		super();
		try { // dado que el id es un numero natural, lo controlo mediante un try/catch
			this.setId(id); // si el setter lanza excepcion por id invalido
		} catch (EIException e) { // la recojo
			e.printStackTrace(); // imprimo la salida
		}
		this.setName(name); // inicializo el nombre del usuario
		this.setnServices(0); // inicializo a cero el numero de servicios hechos por el usuario
		this.setAssignedBicycle(null); // incializo a nula la bicicleta asignada al usuario
	}

	/**
	 * @param id
	 *            el identificador del usuario
	 * @throws EIException
	 *             en caso de que el id no sea un numero natural
	 */
	public void setId(int id) throws EIException {
		if (id < 0) { // si el id es menor que cero no es un numero natural
			throw new EIException("Invalid user id"); // lanzo la excepcion
		} else { // en caso contrario, el numero es natural
			this.id = id; // asigno el id valido
		}
	}

	/**
	 * @return el identificador del usuario
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return el nombre del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            el nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * metodo toString sobre-escrito
	 * 
	 * @return string con los datos del usuario
	 */
	@Override
	public String toString() {
		// retorno el string con el formato especificado en el juego de pruebas
		return new String("id: " + this.getId() + "\nname: " + this.getName() + "\n");
	}

	/**
	 * @return la bicicleta asignada al usuario
	 */
	public Bicycle getAssignedBicycle() {
		return assignedBicycle;
	}

	/**
	 * @param assignedBicycle
	 * 						la bicicleta asignada al usuario
	 */
	public void setAssignedBicycle(Bicycle assignedBicycle) {
		this.assignedBicycle = assignedBicycle; // le asigno la bicicleta al usuario
		this.setnServices(this.getnServices()+1); // sumo en 1 el numero de servicios del usuario
	}
	
	/**
	 * des-asigna una bicicleta de un usuario
	 * aumenta en uno el numero de servicios del usario 
	 */
	public void disetAssignedBicycle() {
		this.assignedBicycle = null; // desasigno la bicicleta al usuario
	}

	/**
	 * @return el numero de servicios que tiene el usuario
	 */
	public int getnServices() {
		return nServices;
	}

	/**
	 * @param nServices
	 * 					el numero de servicios que tiene el usuario
	 */
	public void setnServices(int nServices) {
		this.nServices = nServices;
	}
	
	

}
