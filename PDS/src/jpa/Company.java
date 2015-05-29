package jpa;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JPA Class Company
 */
@Entity
@Table(name="companies")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Set<Event> events; 
	private SuperUser superuser;

	/**
	 * Class constructor methods
	 */
	public Company() {
		super();
	}

	/**
	 *  Methods get/set the fields of database
	 */
	
	/**
	 * Company's index field. Company's id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id", updatable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Company's name.
	 */
	public String getName() {
		return name;
	}
	public  void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Methods get/set persistent relationships
	 */
	
	/**
	 * Company's events.
	 */
	@OneToMany(mappedBy="company")
	public Set<Event> getEventsbyCompany() {
		return events;
	}	
	public void setEventsbyCompany(Set<Event> events) {
 		this.events = events; 
	}
	
	/**
	 * Company's superuser.
	 */
    @ManyToOne
    @JoinColumn(name="superuser_id", referencedColumnName="nif")
	public SuperUser getSuperuser() {
		return superuser;
	}
	public void setSuperuser(SuperUser superuser) {
		this.superuser = superuser;
	}
}
