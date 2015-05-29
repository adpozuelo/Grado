/**
 * AdministrationFacadeRemote local interface.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Set;

import javax.ejb.Remote;

import jpa.Event;
import jpa.User;

@Remote
public interface AdministrationFacadeRemote {
	public int register(User user);

	public int addEvent(Event event);

	public int updateEvent(Event event);

	public int addCategoryToEvent(Integer eventId, Set<String> categories);
}
