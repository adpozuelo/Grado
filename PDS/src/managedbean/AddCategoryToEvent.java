/**
 * AddCategoryToEvent managed bean class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.AdministrationFacadeRemote;

@ManagedBean(name = "addcategorytoevent")
@RequestScoped
public class AddCategoryToEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	// Administration facade injection.
	@EJB
	private AdministrationFacadeRemote facade;

	// Managed bean attributes.
	private Set<String> categories;

	// Constructor.
	public AddCategoryToEvent() {
	}

	/**
	 * Method to add categories to a event in the system.
	 * 
	 * Inject remote administration facade, if exception occurs redirect to
	 * internal server error view. In debug mode we could redirect to specific
	 * error view. If facade return code are success redirect to success view,
	 * else redirect to error view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String start(Integer idEvent) {
		if (categories.size() < 1)
			return "/errors/addCategoriesToEventErrorView1.xhtml";
		Properties props = System.getProperties();
		Context ctx;
		try {
			ctx = new InitialContext(props);
			facade = (AdministrationFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		} catch (NamingException e1) {
			return "/errors/internalServerErrorView.xhtml";
		}
		int sucess = facade.addCategoryToEvent(idEvent, categories);
		if (sucess == 0) {
			return "/confirms/categoriesAddedToEventView.xhtml";
		} else if (sucess == 2) {
			return "/errors/internalServerErrorView.xhtml";
		} else {
			return "/homeView.xhtml";
		}
	}

	/**
	 * Getters & Setters.
	 */

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

}