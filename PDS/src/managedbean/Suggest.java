/**
 * Suggest managed bean class.
 * @author jperezsalva@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.net.Socket;
import java.util.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.component.UIViewRoot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jpa.*;
import ejb.EventFacadeRemote;

/**
 * Managed Bean Suggest
 */
@ManagedBean(name = "suggest")
@SessionScoped
public class Suggest implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventFacadeRemote eventFacade;

	private String mail;

	protected int idEvent = 0;

	Properties props;

	Context ctx;

	public Suggest() throws Exception {

		props = System.getProperties();
		ctx = new InitialContext(props);

		this.mail = new String();
	}

	public String start(int idEvent, String mail) throws Exception {

		String navigation;

		try {
			Socket s = new Socket("www.josalsal.es", 80);

			if (!s.isConnected())
				return "/errors/suggestConnectionErrorView.xhtml";

		} catch (Exception e) {
		}

		try {

			eventFacade = (EventFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		} catch (NamingException e1) {

			return "/errors/internalServerErrorView.xhtml";
		}

		int success = eventFacade.suggest(idEvent, mail);

		switch (success) {

		case 0:

			navigation = "/confirms/suggestSentView.xhtml";
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

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getMail() {

		return mail;
	}

	public void setMail(String mail) {

		this.mail = mail;
	}

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (((String) value).length() < 1)
			throw new ValidatorException(new FacesMessage(
					"You have entered an invalid mail address."));

		if (((String) value).length() > 255)
			throw new ValidatorException(new FacesMessage(
					"Mail Address is too long."));

		Pattern p = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher m = p.matcher((String) value);
		boolean b = m.matches();

		if (b == false)
			throw new ValidatorException(new FacesMessage(
					"Enter a valid mail address."));
	}
}
