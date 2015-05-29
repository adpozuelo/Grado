package managedbean;
/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import ejb.EAgendaFacadeRemote;
/**
 * Managed Bean AddCategoryBean.
 */
@ManagedBean(name = "categoryadd")
@RequestScoped
public class AddCategoryMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private EAgendaFacadeRemote addCatRemote; // EAgenda bussiness facade
	private String catName; // Category name
	private String catDescription; // Category description
	/**
	 * Constructors
	 * @throws Exception
	 */
	public AddCategoryMBean() throws Exception 
	{
		catName = "";
		catDescription = "";
	}
	/**
	 * Getters/Setters
	 * @return
	 */
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) throws Exception {
		this.catDescription = catDescription;
		this.addCategory();
	}
	/**
	 * Method to add category in the database.
	 * @throws Exception
	 */
	private void addCategory() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		addCatRemote = (EAgendaFacadeRemote) ctx.lookup("java:app/pra2.jar/EAgendaFacadeBean!ejb.EAgendaFacadeRemote");
		addCatRemote.addCategory(this.catName, this.catDescription);
	}
}
