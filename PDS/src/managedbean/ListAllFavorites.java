/**
 * ListEvents managed bean class.
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import jpa.*;
import ejb.EventFacadeRemote;

/**
 * Managed Bean ListAllFavorites
 */
@ManagedBean(name = "listfavorites")
@SessionScoped
public class ListAllFavorites implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;

	private Calendar initDate = Calendar.getInstance();
	private Address address = new Address();
	private String name = new String();
	private int id = 0;
	protected Collection<SelectItem> eventListItems = new ArrayList<SelectItem>();
	protected Collection<Event> eventList = new ArrayList<Event>();
	protected User user;

	private int screen = 0;

	public boolean disable = true;

	public ListAllFavorites() throws Exception {
		this.eventList();
		this.eventListItems();
	}

	/**
	 * Method get which return Event Collection
	 * 
	 * @return Collection
	 */
	public Collection<SelectItem> getEventListItems() {
		return eventListItems;
	}

	public Collection<Event> getEventList() {
		return eventList;
	}

	public void eventList() throws Exception {
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		eventFacade = (EventFacadeRemote) ctx
				.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		user = (User) eventFacade.showUser(request.getRemoteUser().trim());

		eventList = (Collection<Event>) eventFacade.listAllFavorites(user
				.getNif().trim());

	}

	public void eventListItems() throws Exception {
		Collection<Event> eventCollection = eventList;

		for (Iterator<Event> iter2 = eventCollection.iterator(); iter2
				.hasNext();) {
			Event ev = (Event) iter2.next();

			SelectItem item = new SelectItem(ev.getId(), ev.getName());

			this.eventListItems.add(item);
		}

	}

	public boolean getDisable() {

		if (eventList.size() == 0) {

			disable = true;
		} else {

			disable = false;
		}
		return disable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setName(int id) {
		this.id = id;
	}

	public Calendar getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address location) { 
		this.address = address;
	}

	/**
	 * allows forward or backward in user screens
	 */
	public void nextScreen() {
		if (((screen + 1) * 10 < eventList.size())) {
			screen += 1;
		}
	}

	public void previousScreen() {
		if ((screen > 0)) {
			screen -= 1;
		}
	}

}
