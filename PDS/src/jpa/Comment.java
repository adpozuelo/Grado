/**
 * Create Table comments from Entity class jpa.
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

/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id field.
	 */
	private Long id;

	/**
	 * Text field.
	 */
	private String text;

	/**
	 * Event field.
	 */
	private Event event;

	/**
	 * User field.
	 */
	private User user;

	/**
	 * Empty constructor
	 */
	public Comment() {

		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param text
	 * @param event
	 * @param user
	 */
	public Comment(String text, Event event, User user) {

		super();

		this.text = text;

		setEvent(event);
		setUser(user);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('hibernate_sequence'::regclass)")
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	@Column(name = "text", length = 255)
	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
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

		Comment other = (Comment) obj;

		if (id == null) {
			if (other.id != null)
				return false;

		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}
