/**
 * Logout managed bean class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "logout")
@SessionScoped
public class Logout implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constructor
	public Logout() {
	}

	/**
	 * Method to logout a user out the system. Request logout operation to JAAS
	 * middleware. Redirect to home view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			return "/errors/logoutErrorView.xhtml";
		}
		return "/homeView.xhtml";
	}
}