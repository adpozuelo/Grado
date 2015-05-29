/**
 * AddEvent managed bean class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

import jpa.Address;
import jpa.Company;
import jpa.Event;
import jpa.Keyword;
import jpa.Picture;
import jpa.SuperUser;
import ejb.AdministrationFacadeRemote;
import ejb.CompanyFacadeRemote;

@ManagedBean(name = "addEvent")
@ViewScoped
public class AddEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	// Administration facade injection.
	@EJB
	private AdministrationFacadeRemote facade;
	// Company facade injection.
	@EJB
	private CompanyFacadeRemote companyFacade;
	// Event attributes in managed bean.
	private String name;
	private String description;
	private String keywords;
	private Date initDate;
	private Date initDateControl; // Initial date control attribute
	private Date endDate;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String company;
	private String superUserNif;
	private Part picturePart;
	private Picture picture;

	/**
	 * Constructor to initialize dates.
	 */
	public AddEvent() {
		initDateControl = new Date();
		initDate = initDateControl;
		endDate = initDateControl;
	}

	/**
	 * Method to add event in the system.
	 * 
	 * Inject remote administration facade, if exception occurs redirect to
	 * internal server error view. In debug mode we could redirect to specific
	 * error view. Create new event and set all attributes which come from view.
	 * We compare view initial date with control initial date to ensure date
	 * consistency. Also compare end date with initial date to ensure date
	 * consistency. If date consistency is broken redirect to specific error
	 * view. We ensure file maximum size is 4MB and file type is "image/jpeg".
	 * All data from view are trimmed to avoid ' ' character injection in the
	 * system. Finally, if facade return code are success redirect to success
	 * view else redirect to error view.
	 * 
	 * Exception handling: if exceptions occurs redirect web application to
	 * errors views.
	 */
	public String start() {
		Event event = new Event();
		Calendar initDateControlCalendar = Calendar.getInstance();
		initDateControlCalendar.setTime(initDateControl);

		initDateControlCalendar.set(Calendar.MILLISECOND, 0);
		initDateControlCalendar.set(Calendar.SECOND, 0);

		Calendar initDateCalendar = Calendar.getInstance();
		initDateCalendar.setTime(initDate);
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(endDate);

		initDateCalendar.set(Calendar.MILLISECOND, 999);
		initDateCalendar.set(Calendar.SECOND, 1);
		endDateCalendar.set(Calendar.MILLISECOND, 999);
		endDateCalendar.set(Calendar.SECOND, 2);

		if (initDateCalendar.before(initDateControlCalendar)) {
			return "/errors/initDateErrorView1.xhtml";
		} else if (initDateCalendar.after(endDateCalendar)) {
			return "/errors/initDateErrorView2.xhtml";
		} else {
			event.setInitDate(initDateCalendar);
		}
		if (endDateCalendar.before(initDateCalendar)) {
			return "/errors/endDateErrorView.xhtml";
		} else {
			event.setEndDate(endDateCalendar);
		}
		if (picturePart.getSize() > 4194304) {
			return "/errors/fileErrorView2.xhtml";
		}
		if (!"image/jpeg".equals(picturePart.getContentType())) {
			return "/errors/fileErrorView3.xhtml";
		}
		picture = new Picture();
		picture.setPictureDataType(picturePart.getContentType());
		try {
			InputStream input = picturePart.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4194304];
			for (int length = 0; (length = input.read(buffer)) > 0;)
				output.write(buffer, 0, length);
			picture.setPictureData(output.toByteArray());
		} catch (Exception e) {
			return "/errors/fileErrorView1.xhtml";
		}
		event.setPicture(picture);
		Properties props = System.getProperties();
		Context ctx;
		try {
			ctx = new InitialContext(props);
			facade = (AdministrationFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
		} catch (NamingException e) {
			return "/errors/internalServerErrorView.xhtml";
		}
		event.setName(this.name.trim());
		event.setDescription(description.trim());
		Set<Keyword> keywordsSet = new TreeSet<Keyword>();
		StringTokenizer st = new StringTokenizer(keywords.trim(), ",");
		while (st.hasMoreTokens()) {
			keywordsSet.add(new Keyword(st.nextToken().trim()));
		}
		event.setKeyword(keywordsSet);
		Address address = new Address();
		address.setCity(city.trim());
		address.setCountry(country.trim());
		address.setState(state.trim());
		address.setStreet(street.trim());
		address.setZipCode(zipCode.trim());
		event.setAddress(address);
		Company companyToAdd = new Company();
		companyToAdd.setId(Integer.parseInt(company.trim()));
		event.setCompany(companyToAdd);
		SuperUser admin = new SuperUser();
		admin.setNif(superUserNif.trim());
		event.setSuperuser(admin);
		int sucess = facade.addEvent(event);
		if (sucess == 0) {
			return "/confirms/eventCreatedView.xhtml";
		} else if (sucess == 2) {
			return "/errors/internalServerErrorView.xhtml";
		} else {
			return "/homeView.xhtml";
		}
	}

	/**
	 * Getters & Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {

		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSuperUserNif() {
		return superUserNif;
	}

	public void setSuperUserNif(String superUserNif) {
		this.superUserNif = superUserNif;
	}

	public Date getInitDateControl() {
		return initDateControl;
	}

	public void setInitDateControl(Date initDateControl) {
		this.initDateControl = initDateControl;
	}

	public Part getPicturePart() {
		return picturePart;
	}

	public void setPicturePart(Part picturePart) {
		this.picturePart = picturePart;
	}

}