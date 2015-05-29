/**
 * User entity JPA class.
 * ORM to 'users' table.
 * Inheritance strategy based in one table per super and sub class
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING, length = 1)
@DiscriminatorValue(value = "U")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	/**
	 * User's NIF.
	 */
	private String nif;

	@Id
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * User's name.
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * User's surname.
	 */
	private String surname;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * User's prefered language.
	 */
	private Language preferedLanguage;

	@Embedded
	public Language getPreferedLanguage() {
		return preferedLanguage;
	}

	public void setPreferedLanguage(Language preferedLanguage) {
		this.preferedLanguage = preferedLanguage;
	}

	/**
	 * User's address.
	 */
	private Address address;

	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * User's password.
	 */
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * User's email.
	 */
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * User's favorite events
	 */
	private Set<Event> favoriteEvents;

	@ManyToMany(mappedBy = "user")
	public Set<Event> getFavoriteEvents() {
		return favoriteEvents;
	}

	public void setFavoriteEvents(Set<Event> favoriteEvents) {
		this.favoriteEvents = favoriteEvents;
	}

	/**
	 * User's active flag.
	 */
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * User's comments.
	 */
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST })
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * User's rattings.
	 */
	private Set<Ratting> rattings = new HashSet<Ratting>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST })
	public Set<Ratting> getRattings() {
		return rattings;
	}

	public void setRattings(Set<Ratting> rattings) {
		this.rattings = rattings;
	}
}
