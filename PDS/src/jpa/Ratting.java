/**
 * Create Table rattings from Entity class jpa.
 * 
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */

package jpa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rattings")
public class Ratting implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id field.
	 */
	private Long id;

	/**
	 * Ratting field.
	 */
	private Integer ratting;

	/**
	 * Event field.
	 */
	private Event event;

	/**
	 * User field.
	 */
	private User user;

	/**
	 * Empty constructor.
	 */
	public Ratting() {

		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param ratting
	 * @param event
	 */
	public Ratting(Integer ratting, Event event, User user) {

		super();

		this.ratting = ratting;

		setEvent(event);
		setUser(user);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('hibernate_sequence'::regclass)")
	public Long getId() {

		return this.id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer getRatting() {

		return ratting;
	}

	public void setRatting(Integer ratting) {

		this.ratting = ratting;
	}

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Event.class, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "event_id", nullable = true, referencedColumnName = "id")
	public Event getEvent() {

		return event;
	}

	public void setEvent(Event event) {

		this.event = event;
	}

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "user_id", nullable = true, referencedColumnName = "nif")
	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Ratting other = (Ratting) obj;

		if (id == null) {
			if (other.id != null)
				return false;

		} else if (!id.equals(other.id))

			return false;

		return true;
	}

}
