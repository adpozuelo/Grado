/**
 * AdministrationFacadeBean stateless session bean class.
 * @author adpozuelo@iqfr.csic.es
 * @version 1.0
 */
package ejb;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.Category;
import jpa.Event;
import jpa.User;

@Stateless
public class AdministrationFacadeBean implements AdministrationFacade,
		AdministrationFacadeRemote {

	// Inject persistence context.
	@PersistenceContext(unitName = "eagenda")
	private EntityManager entman;

	// Constructor.
	public AdministrationFacadeBean() {
	}

	/**
	 * Method to register users in the system.
	 * 
	 * if user exist in the persistence subsystem return error code (user exist)
	 * else persist new user in the persistence subsystem and return success
	 * code (user added).
	 * 
	 * Exception handling: if exceptions occurs return error code (internal
	 * server error).
	 */
	@Override
	public int register(User user) {
		@SuppressWarnings("unchecked")
		Collection<User> allUsersEmail = (Collection<User>) entman
				.createQuery("from User U WHERE U.email = ?1")
				.setParameter(1, user.getEmail()).getResultList();
		Iterator<User> it = allUsersEmail.iterator();
		while (it.hasNext()) {
			if (it.next().getEmail().equals(user.getEmail()))
				return 1;
		}
		User userExist = entman.find(User.class, user.getNif());
		if (userExist != null) {
			return 1;
		} else {
			try {
				entman.persist(user);
				return 0;
			} catch (Exception e) {
				return 2;
			}
		}
	}

	/**
	 * Method to add events in the system.
	 * 
	 * Add event in the persistence subsystem. No duplicate event control
	 * exists.
	 * 
	 * Exception handling: if exceptions occurs return error code (internal
	 * server error).
	 */
	@Override
	public int addEvent(Event event) {
		try {
			entman.persist(event);
			return 0;
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Method to update events in the system.
	 * 
	 * Update event in the persistence subsystem.
	 * 
	 * Exception handling: if exceptions occurs return error code (internal
	 * server error).
	 */
	@Override
	public int updateEvent(Event event) {
		try {
			entman.merge(event);
			return 0;
		} catch (Exception e) {
			return 2;
		}
	}

	/**
	 * Method to add categories to events in the system.
	 * 
	 * Find for event by eventId parameter in the persistence subsystem.
	 * Add categories to event and return success code (categories added).
	 * 
	 * Exception handling: if exceptions occurs return error code (internal
	 * server error).
	 */
	@Override
	public int addCategoryToEvent(Integer eventId, Set<String> categories) {
		try {
			Event event = entman.find(Event.class, new Long(eventId));
			Iterator<String> it = categories.iterator();
			while (it.hasNext()) {
				event.getCategories().add(
						entman.find(Category.class,
								Integer.parseInt(it.next().trim())));
			}
			return 0;
		} catch (Exception e) {
			return 2;
		}
	}
}