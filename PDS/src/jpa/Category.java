package jpa;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

/**
 * JPA Class Category
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String description;
	private Set<Event> events; 
	private SuperUser superuser;

	/**
	 * Class constructor methods
	 */
	public Category() {
		super();
	}

	/**
	 *  Methods get/set the fields of database
	 */
	
	/**
	 * Category's index field. Category's id.
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
	 * Category's name.
	 */
	public String getName() {
		return name;
	}
	public  void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Category's description.
	 */
	public String getDescription() {
		return description;
	}
	public  void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Methods get/set persistent relationships
	 */
	
	/**
	 * Category's events.
	 */
	@ManyToMany(mappedBy="categories")
	public Set<Event> getEventsbyCategory() {
		return events;
	}	
	public void setEventsbyCategory(Set<Event> events) {
 		this.events = events; 
	}

	/**
	 * Category's superuser.
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
