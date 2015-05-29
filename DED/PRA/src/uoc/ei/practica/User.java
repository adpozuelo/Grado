package uoc.ei.practica;

/**
 * Clase que representa un usuario en el sistema
 *
 */
public class User extends IdentifiedObject implements Comparable<User>{
	
	/** 
	 * nombre del usuario
	 */
	private String name;
	
	/**
	 * apuntador al servicio actual del usuario
	 */
	private Service currentService;

	/**
	 * actividad del usuario
	 */
	private int activity;
	

	public User(String userId, String name) {
		super(userId);
		this.set(name);
		this.activity=0;
	}

	public void set(String name) {
		this.name=name;
	}

	
	/**
	 * método que proporciona una representación en forma de String de un usuario
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer(super.toString());
		sb.append("name: ").append(this.name).append(Messages.LS);
		
		return sb.toString();
		
	}

	/**
	 * método que proporciona el nombre del usuario
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * método que actualiza el Servicio actual
	 * @param service el servicio actual
	 */
	public void addCurrentService(Service service) {
		this.currentService=service;
	}

	/**
	 * método que indica si actualmente hay un servicio activo
	 * @return
	 */
	public boolean hasCurrentService() {
		return this.currentService!=null;
	}

	/**
	 * método que finaliza el servicio actual
	 */
	public void finishCurrentService() {
		this.currentService=null;
	}

	/**
	 * método que incrementa la actividad del usuario
	 */
	public void incActicity() {
		this.activity++;
	}

	/**
	 * método que proporcioona la actividad del usuario
	 * @return
	 */
	public int activity() {
		return this.activity;
	}

	@Override
	public int compareTo(User o) {
		return this.identifier.compareTo(o.identifier);
	}

}
