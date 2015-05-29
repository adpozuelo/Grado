package jpa;
/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.io.Serializable;
import javax.persistence.*;
/**
 * JPA Class CategoryJPA
 */
@Entity
@Table(name="pra2.category")
public class CategoryJPA implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id; // category id
	private String name; // category name
	private String description; // category description
	/**
	 * Constructors
	 */
	public CategoryJPA() {
		super();
	}
	public CategoryJPA(int id, String name, String description) {		
		this.id = id;
		this.name = name;
		this.description = description;
		}
	/**
	 *  Getters/Setters
	 */
	@Id
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