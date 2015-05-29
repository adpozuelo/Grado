/**
 * EventFacade local interface.
 * 
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */

package ejb;

import java.util.Collection;
import javax.ejb.Local;

import jpa.*;

@Local
public interface EventFacade {

	public int sendComment(String user, int eventId, String comment);

	public int addRatting(String user, int eventId, Integer ratting);
	
	public int avgRatting(int eventId);

	public int suggest(int eventId, String email);

	public int addToFavorites(String user, int eventId);

	public Collection<Event> listAllFavorites(String user);

	public Collection<Event> listAllEvents();

	public Collection<Event> findEventsByCategory(int categoryId);

	public Collection<Event> findEventsByName(String name);

	public Collection<Event> findEventsByWord(String word);

	public Event showEvent(int eventId);

	public User showUser(String email);

}
