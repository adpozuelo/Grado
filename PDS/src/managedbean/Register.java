/**
 * Register managed bean class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jpa.Address;
import jpa.Language;
import jpa.SuperUser;
import jpa.User;
import ejb.AdministrationFacadeRemote;

@ManagedBean(name = "register")
@RequestScoped
public class Register implements Serializable {
	private static final long serialVersionUID = 1L;
	// Administration facade injection.
	@EJB
	private AdministrationFacadeRemote facade;
	// User attributes in managed bean.
	private String nif;
	private String name;
	private String language;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String email;
	private String password;
	private String surname;
	// Email pattern for regular expression.
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// Constructor
	public Register() {
	}

	/**
	 * Method to register a user in the system.
	 * 
	 * Inject remote administration facade, if exception occurs redirect to
	 * internal server error view. In debug mode we could redirect to specific
	 * error view. Create new user and set all attributes which come from view.
	 * All data from view are trimmed to avoid ' ' character injection in the
	 * system. Email is validated by regular expression. Password is not trimmed
	 * because ' ' character is valid at start or end point of the password.
	 * Password is encrypted using MD5, "0xff" key and '&' binary operator.
	 * Finally, if facade return code are success redirect to success view else
	 * redirect to error view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String start() {
		User user = new User();
		if (emailValidator(email.trim()))
			user.setEmail(email.trim());
		else
			return "/errors/emailErrorView1.xhtml";
		Properties props = System.getProperties();
		Context ctx;
		try {
			ctx = new InitialContext(props);
			facade = (AdministrationFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		} catch (NamingException e1) {
			return "/errors/internalServerErrorView.xhtml";
		}
		Address address = new Address();
		address.setCity(city.trim());
		address.setCountry(country.trim());
		address.setState(state.trim());
		address.setStreet(street.trim());
		address.setZipCode(zipCode.trim());
		Language preferedLanguage = new Language();
		preferedLanguage.setLanguage(language.trim());
		user.setNif(nif.trim());
		user.setName(name.trim());
		user.setSurname(surname.trim());
		user.setPreferedLanguage(preferedLanguage);
		user.setAddress(address);
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = password.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			password = sb.toString();
		} catch (Exception e) {
			return "/errors/internalServerErrorView.xhtml";
		}
		user.setPassword(password);
		user.setActive(Boolean.TRUE);
		int sucess = facade.register(user);
		if (sucess == 0) {
			return "/confirms/userCreatedView.xhtml";
		} else if (sucess == 1) {
			return "/errors/userExistErrorView.xhtml";
		} else if (sucess == 2) {
			return "/errors/internalServerErrorView.xhtml";
		} else {
			return "homeView.xhtml";
		}
	}

	/**
	 * Method to register the first super user in the system. Initialization
	 * view's button appears if no super user exists in the system.
	 * 
	 * Inject remote administration facade, if exception occurs redirect to
	 * internal server error view. In debug mode we could redirect to specific
	 * error view.
	 * 
	 * Create new super user and set all attributes to default data.
	 * 
	 * Finally, if facade return code are success redirect to success view else
	 * redirect to error view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String initSuperUser() {
		Properties props = System.getProperties();
		Context ctx;
		try {
			ctx = new InitialContext(props);
			facade = (AdministrationFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		} catch (NamingException e1) {
			return "/errors/internalServerErrorView.xhtml";
		}
		SuperUser superUser = new SuperUser();
		superUser.setNif("1");
		superUser.setName("root");
		superUser.setEmail("root@uoc.edu");
		superUser.setPassword("81dc9bdb52d04dc2036dbd8313ed055");
		superUser.setActive(Boolean.TRUE);
		int sucess = facade.register(superUser);
		if (sucess == 0) {
			return "/confirms/superUserCreatedView.xhtml";
		} else if (sucess == 1) {
			return "/errors/userExistErrorView.xhtml";
		} else if (sucess == 2) {
			return "/errors/internalServerErrorView.xhtml";
		} else {
			return "homeView.xhtml";
		}
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

	/**
	 * Getters & Setters.
	 */

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}