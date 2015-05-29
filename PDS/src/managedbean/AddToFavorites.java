/**
 * AddToFavorites managed bean class.
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ejb.EventFacadeRemote;
import jpa.*;

@ManagedBean(name = "addtofavorites")
@RequestScoped
public class AddToFavorites implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;

	private Properties props;
	private Context ctx;

	protected Event dataEvent;

	protected int idEvent = 1;

	protected User user;

	public AddToFavorites() throws Exception {

		props = System.getProperties();
		ctx = new InitialContext(props);
		setDataEvent();
	}

	public String start(Integer idEvent) {

		String navigation;

		try {

			eventFacade = (EventFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		} catch (NamingException e1) {

			return "/errors/internalServerErrorView.xhtml";
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		user = (User) eventFacade.showUser(request.getRemoteUser().trim());

		int success = eventFacade.addToFavorites(user.getNif().trim(), idEvent);

		switch (success) {

		case 0:

			navigation = "/confirms/favoritesAddedToEventView.xhtml";
			break;

		case 1:

			navigation = "/errors/addToFavoritesExistsErrorView.xhtml";
			break;

		case 2:

			navigation = "/errors/internalServerErrorView.xhtml";
			break;

		default:

			navigation = "/homeView.xhtml";
			break;
		}

		return navigation;
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
		setDataEvent();
	}

	public Event getDataEvent() {
		return dataEvent;
	}

	public void setDataEvent() throws Exception {
		
		props = System.getProperties();
		ctx = new InitialContext(props);
		
		eventFacade = (EventFacadeRemote) ctx
				.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");
		dataEvent = (Event) eventFacade.showEvent(idEvent);
	}

}
