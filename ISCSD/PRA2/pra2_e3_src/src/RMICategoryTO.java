/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.io.Serializable;
/**
 * Class that follows the pattern TO, which encapsulates data and a category
 * 
 */
@SuppressWarnings("serial")
public class RMICategoryTO implements Serializable {
	private String name; // Category's name
	private String description; // Category's description
	private String id; // Category's id
	/**
	 * Constructors 
	 */
	public RMICategoryTO () {
		this.id = "";
		this.name = "";
		this.description = "";
	}
	public RMICategoryTO (String id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
	}
	/**
	 * Getters/Setters
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public String toString()
	{
		String pet = id+" "+name+" "+description;
		return pet;
	}		
}