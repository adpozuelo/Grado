/**
 * Event entity JPA class.
 * ORM to 'events' table.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	public Event() {
		super();
	}

	/**
	 * Event's index field. Event's id.
	 */
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Event's name.
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Event's description.
	 */
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Event's picture
	 */
	private Picture picture;

	@Embedded
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * Event's address location.
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
	 * Event's initial date.
	 */
	private Calendar initDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getInitDate() {
		return initDate;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	/**
	 * Event's end date.
	 */
	private Calendar endDate;

	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Event's keywords
	 */
	private Set<Keyword> keyword;

	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	public Set<Keyword> getKeyword() {
		return keyword;
	}

	public void setKeyword(Set<Keyword> keyword) {
		this.keyword = keyword;
	}

	/**
	 * Event's user.
	 */
	private Set<User> user;

	@ManyToMany
	@JoinTable(name = "favorites", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	/**
	 * Event's superuser.
	 */
	private SuperUser superuser;

	@ManyToOne
	@JoinColumn(name = "superuser_id", referencedColumnName = "nif")
	public SuperUser getSuperuser() {
		return superuser;
	}

	public void setSuperuser(SuperUser superuser) {
		this.superuser = superuser;
	}

	/**
	 * Event's categories.
	 */
	private Set<Category> categories;

	@ManyToMany
	@JoinTable(joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * Event's company
	 */
	private Company company;

	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Event's comments.
	 */
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(mappedBy = "event", cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@OrderBy("id DESC")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Event's rattings.
	 */
	private Set<Ratting> rattings = new HashSet<Ratting>();

	@OneToMany(mappedBy = "event", cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	public Set<Ratting> getRattings() {
		return rattings;
	}

	public void setRattings(Set<Ratting> rattings) {
		this.rattings = rattings;
	}
}
