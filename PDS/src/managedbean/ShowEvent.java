/**
 * ShowEvent managed bean class.
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import jpa.*;
import ejb.EventFacadeRemote;

/**
 * Managed Bean ShowEvent
 */
@ManagedBean(name = "eventshow")
@SessionScoped
public class ShowEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;

	Properties props;
	Context ctx;

	protected Event dataEvent;

	protected int idEvent = 1;

	protected String keywords;

	protected int ratting = 0;

	public ShowEvent() throws Exception {

		props = System.getProperties();
		ctx = new InitialContext(props);
	}

	/**
	 * Get/set the id number and Event instance
	 * 
	 * @return Event Id
	 */
	public int getIdEvent() {

		return idEvent;
	}

	public void setIdEvent(int idEvent) throws Exception {

		this.idEvent = idEvent;
		setDataEvent(idEvent);
	}

	public Event getDataEvent() {

		return dataEvent;
	}

	public String getKeywords() {

		return keywords;
	}

	public void setDataEvent(int idEvent) throws Exception {

		eventFacade = (EventFacadeRemote) ctx
				.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		dataEvent = (Event) eventFacade.showEvent(idEvent);

		Set<Keyword> words = dataEvent.getKeyword();

		keywords = "";

		Iterator<Keyword> iterator = words.iterator();

		while (iterator.hasNext())
			keywords += iterator.next().getKeyword() + ", ";

		keywords = (keywords.length() > 0) ? keywords.substring(0,
				keywords.length() - 2)
				+ "." : "";

		ratting = eventFacade.avgRatting(idEvent);
		setRatting(ratting);

	}

	public void setRatting(int ratting) {
		
		this.ratting = ratting;

	}

	public int getRatting() {
		
		return ratting;
	}
}
