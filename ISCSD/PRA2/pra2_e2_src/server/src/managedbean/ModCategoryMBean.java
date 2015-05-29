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
import jpa.CategoryJPA;
import ejb.EAgendaFacadeRemote;
/**
 * Managed Bean ModCategoryMBean
 */
@ManagedBean(name = "categorymod")
@RequestScoped
public class ModCategoryMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private EAgendaFacadeRemote modCatRemote; // EAgenda bussiness facade
	private CategoryJPA dataCat; // Category to modify
	private int idCat = 1; // Category id
	private String catName; // Category name
	private String catDescription; // Category description
	/**
	 * Constructors
	 * Contructor call to private initizaled category method and initialize MBean atributes with original values
	 * of the category. In this way, when the form is loaded the original values are located in the form fields.
	 * @throws Exception
	 */
	public ModCategoryMBean() throws Exception 
	{
		setDataCat(); // Call to private initialized category method
		this.idCat = this.dataCat.getId(); // Initizale idCat atribute with category's id
		this.catName = this.dataCat.getName(); // Initizale name atribute with category's name
		this.catDescription = this.dataCat.getDescription(); // Initizale description atribute with category's description
	}
	/**
	 * Getters/Setters
	 * @return
	 */
	public int getIdCat()
	{
		return idCat;
	}
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
		this.updateDataCat();
	}
	public void setIdCat(int idCat) throws Exception
	{
		this.idCat = idCat;
		setDataCat(); // Every change in idCat needs a MBean atributes update
		this.catName = this.dataCat.getName();
		this.catDescription = this.dataCat.getDescription();
	}
	public CategoryJPA getDataCat()
	{
		return dataCat;
	}	
	/**
	 * Method to retrieve the category to modify and use its data to show them into the category modification formulary
	 * @throws Exception
	 */
	private void setDataCat() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modCatRemote = (EAgendaFacadeRemote) ctx.lookup("java:app/pra2.jar/EAgendaFacadeBean!ejb.EAgendaFacadeRemote");
		dataCat = (CategoryJPA) modCatRemote.showCategory(idCat);
	}
	/**
	 * Method to update the category which has been modified after submit
	 * @throws Exception
	 */
	private void updateDataCat() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		modCatRemote = (EAgendaFacadeRemote) ctx.lookup("java:app/pra2.jar/EAgendaFacadeBean!ejb.EAgendaFacadeRemote");
		dataCat = (CategoryJPA)modCatRemote.updateCategory(this.idCat, this.catName, this.catDescription);
	}
}
