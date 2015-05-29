/**
 * AdministrationFacade local interface.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Set;

import javax.ejb.Local;

import jpa.Event;
import jpa.User;

@Local
public interface AdministrationFacade {
	public int register(User user);

	public int addEvent(Event event);

	public int updateEvent(Event event);

	public int addCategoryToEvent(Integer eventId, Set<String> categories);
}
