/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
package to;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * TO Class CategoryTO
 */
@XmlRootElement(name="category")
public class CategoryTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	/**
	 * Class constructor methods
	 */
	public CategoryTO() {
		super();
	}
	public CategoryTO(int id, String name, String description) {		
		this.id = id;
		this.name = name;
		this.description = description;
	}
	/**
	 * Getters/Setters
	 * @return
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public  void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}	
	public void setDescription(String description) {
		this.description = description;
	}
}
