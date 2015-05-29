/**
 * UpdateEvent managed bean class.
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

import jpa.Event;
import jpa.Keyword;
import jpa.Picture;
import ejb.AdministrationFacadeRemote;
import ejb.EventFacadeRemote;

@ManagedBean(name = "modifyevent")
@ViewScoped
public class UpdateEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private AdministrationFacadeRemote facade;
	@EJB
	private EventFacadeRemote eventFacade;
	// Event attributes in managed bean.
	protected Event dataEvent;
	protected int idEvent = 1;
	private Date initDate;
	private Date initDateControl; // Initial date control attribute
	private Date endDate;
	private Part picturePart;
	private Picture picture;
	private String keywords;

	/**
	 * Constructor to initialize dates.
	 */
	public UpdateEvent() throws Exception {
		initDataEvent();
		initDateControl = new Date();
		picturePart = null;
		keywords = new String();
	}

	/**
	 * Method to update event in the system.
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
	public String start(Event event) {
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
		if (picturePart != null) {
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
		}
		Set<Keyword> keywordsSet = new TreeSet<Keyword>();
		StringTokenizer st = new StringTokenizer(keywords.trim(), ",");
		while (st.hasMoreTokens()) {
			keywordsSet.add(new Keyword(st.nextToken().trim()));
		}
		event.setKeyword(keywordsSet);
		Properties props = System.getProperties();
		Context ctx;
		try {
			ctx = new InitialContext(props);
			facade = (AdministrationFacadeRemote) ctx
					.lookup("java:app/eAgenda.jar/AdministrationFacadeBean!ejb.AdministrationFacadeRemote");
			int sucess = facade.updateEvent(event);
			if (sucess == 0) {
				return "/confirms/eventModifiedView.xhtml";
			} else if (sucess == 2) {
				return "/errors/internalServerErrorView.xhtml";
			} else {
				return "/homeView.xhtml";
			}
		} catch (NamingException e) {
			return "/errors/internalServerErrorView.xhtml";
		}
	}

	/**
	 * Method to initialize data of the event.
	 * 
	 * @throws Exception
	 */
	public void initDataEvent() throws Exception {

		Properties props = System.getProperties();

		Context ctx = new InitialContext(props);
		eventFacade = (EventFacadeRemote) ctx
				.lookup("java:app/eAgenda.jar/EventFacadeBean!ejb.EventFacadeRemote");

		dataEvent = (Event) eventFacade.showEvent(idEvent);
	}

	/**
	 * Getters & Setters.
	 * 
	 * @return
	 */
	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) throws Exception {
		this.idEvent = idEvent;
		initDataEvent();
	}

	public Event getDataEvent() {
		return dataEvent;
	}

	public void setDataEvent(Event dataEvent) {
		this.dataEvent = dataEvent;
	}

	public Date getInitDate() {
		this.initDate = dataEvent.getInitDate().getTime();
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		endDate = dataEvent.getEndDate().getTime();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Part getPicturePart() {
		return picturePart;
	}

	public void setPicturePart(Part picturePart) {
		this.picturePart = picturePart;
	}

	/**
	 * Getter of keywords has to format output String.
	 * 
	 * @return
	 */
	public String getKeywords() {
		Set<Keyword> keywordSet = dataEvent.getKeyword();
		if (keywordSet != null) {
			Keyword[] keywordArray = keywordSet.toArray(new Keyword[keywordSet
					.size()]);
			for (int i = 0; i < keywordArray.length; i++) {
				if (i < keywordArray.length - 1)
					keywords += keywordArray[i].getKeyword() + ", ";
				else
					keywords += keywordArray[i].getKeyword();
			}
		}
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}