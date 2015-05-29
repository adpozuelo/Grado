/**
 * AddRatting managed bean class.
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.component.UIViewRoot;

import ejb.EventFacadeRemote;
import jpa.*;

@ManagedBean(name = "addratting")
@RequestScoped
public class AddRatting implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;
	
	private Properties props;
	private Context ctx;

	protected Event dataEvent;

	protected Integer idEvent = 1;

	protected User user;

	protected String ratting;

	public AddRatting() throws Exception {
		
		props = System.getProperties();
		ctx = new InitialContext(props);
		
		setDataEvent();
	}

	public String start(Integer idEvent, String ratting) {

		String navigation;

		Integer rat = Integer.parseInt(ratting);

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

		int success = eventFacade
				.addRatting(user.getNif().trim(), idEvent, rat);

		switch (success) {

		case 0:

			navigation = "/confirms/addRattingAddedView.xhtml";
			break;

		case 1:

			navigation = "/errors/rattingForEventUserExistsErrorView.xhtml";
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

	public String getRatting() {

		return ratting;
	}

	public void setRatting(String ratting) {

		this.ratting = ratting;
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

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		boolean isANumber = true;

		if (((String) value).length() < 1)
			throw new ValidatorException(new FacesMessage(
					"Enter a valid ratting value between 1 and 5"));

		if (((String) value).length() > 1)
			throw new ValidatorException(new FacesMessage(
					"Enter a valid ratting value between 1 and 5"));

		for (int i = 0; i < ((String) value).length(); i++) {

			if (!Character.isDigit(((String) value).charAt(i)))
				isANumber = false;
		}

		if (isANumber == false)
			throw new ValidatorException(new FacesMessage(
					"It's not an acceptable value for Category."));

		if (Integer.valueOf((String) value) > 5) {
			throw new ValidatorException(new FacesMessage(
					"Ratting is greater than allowable maximum of '5'"));
		}

		if (Integer.valueOf((String) value) < 1) {
			throw new ValidatorException(new FacesMessage(
					"Ratting is shorter than allowable minimum of '1'"));
		}
	}
}
