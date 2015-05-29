/**
 * Login managed bean class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	// Managed bean attributes.
	private String email;
	private String password;
	// Email pattern for regular expression.
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// Constructor.
	public Login() {
		email = new String();
		password = new String();
	}

	/**
	 * Method to login a user in the system.
	 * 
	 * Password is not trimmed because ' ' character is valid at start or end
	 * point of the password. Password is encrypted using MD5, "0xff" key and
	 * '&' binary operator.
	 * 
	 * Encrypt the password and request login operation to JAAS middleware.
	 * 
	 * Finally, if user is super user redirect application to super user
	 * administration view, else if user is registered user redirect to user
	 * administration view and else (error occurs) redirect to error view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String start() {
		if (!emailValidator(email.trim()))
			return "/errors/emailErrorView2.xhtml";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = this.password.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			this.password = sb.toString();
		} catch (Exception e) {
			return "/errors/internalServerErrorView.xhtml";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(email.trim(), this.password);
		} catch (ServletException e) {
			return "/errors/loginErrorView.xhtml";
		}
		if (request.isUserInRole("A"))
			return "/admin/homeView.xhtml";
		else if (request.isUserInRole("U"))
			return "/user/homeView.xhtml";
		else
			return "/errors/loginErrorView.xhtml";
	}

	/**
	 * Method to know if a user is a super user.
	 */
	public boolean isUserAdmin() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.isUserInRole("A");
	}

	/**
	 * Method to know if a user is a registered user.
	 */
	public boolean isUserRegistered() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.isUserInRole("U");
	}

	/**
	 * Method to know if user is logged in the system.
	 */
	public boolean isUserLogged() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.isUserInRole("A") || request.isUserInRole("U");
	}

	/**
	 * Method to know which user is logged.
	 * 
	 * @return
	 */
	public String UserLogged() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		String userLogged = "Anonymous";

		if (request.getRemoteUser() != null) {

			userLogged = request.getRemoteUser();

		}
		return userLogged;
	}

	/**
	 * Getters & Setters.
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Email pattern matcher method
	 */
	private boolean emailValidator(String email) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}