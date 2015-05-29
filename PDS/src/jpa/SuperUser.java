/**
 * SuperUser entity JPA class.
 * ORM to 'superusers' table.
 * Inheritance strategy based in one table per super and sub class
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "A")
public class SuperUser extends User {

	private static final long serialVersionUID = 1L;

	public SuperUser() {
		super();
	}

	/**
	 * SuperUser's managed categories
	 */
	private Set<Category> managedCategories;

	@OneToMany(mappedBy = "superuser")
	public Set<Category> getCategories() {
		return managedCategories;
	}

	public void setCategories(Set<Category> categories) {
		this.managedCategories = categories;
	}

	/**
	 * SuperUser's managed companies
	 */
	private Set<Company> managedCompanies;

	@OneToMany(mappedBy = "superuser")
	public Set<Company> getCompanies() {
		return managedCompanies;
	}

	public void setCompanies(Set<Company> companies) {
		this.managedCompanies = companies;
	}

	/**
	 * SuperUser's managed events
	 */
	private Set<Event> managedEvents;

	@OneToMany(mappedBy = "superuser")
	public Set<Event> getEvents() {
		return managedEvents;
	}

	public void setEvents(Set<Event> events) {
		this.managedEvents = events;
	}
}
