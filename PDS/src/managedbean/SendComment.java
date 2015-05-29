/**
 * SendComment managed bean class.
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

@ManagedBean(name = "sendcomment")
@RequestScoped
public class SendComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;
	
	private Properties props;
	private Context ctx;

	protected Event dataEvent;

	protected int idEvent = 1;

	protected User user;

	protected String comment;

	public SendComment() throws Exception {
		
		props = System.getProperties();
		ctx = new InitialContext(props);
		setDataEvent();
	}

	public String start(Integer idEvent, String comment) {

		String navigation;

		try {

			eventFacade = (EventFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		} catch (NamingException e) {

			return "/errors/internalServerErrorView.xhtml";
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		user = (User) eventFacade.showUser(request.getRemoteUser().trim());

		int success = eventFacade.sendComment(user.getNif().trim(), idEvent,
				comment);

		switch (success) {

		case 0:

			navigation = "/confirms/sendCommentAddedView.xhtml";
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if (((String) arg2).length() > 255) {
			throw new ValidatorException(new FacesMessage(
					"Length is greater than allowable maximum of '255'"));
		}
		if (((String) arg2).length() < 1) {
			throw new ValidatorException(new FacesMessage(
					"Length is shorter than allowable minimum of '1'"));
		}
	}

	public void clear() {
		this.setComment("");
	}
}
