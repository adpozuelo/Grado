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
 * Managed Bean ShowCategoryMBean
 */
@ManagedBean(name = "category")
@SessionScoped
public class ShowCategoryMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private EAgendaFacadeRemote showCatRemote; // EAgenda bussiness facade
	private CategoryJPA dataCat; // Category to modify
	private int idCat = 1; // Category id
	/**
	 * Constructors
	 * @throws Exception
	 */
	public ShowCategoryMBean() throws Exception 
	{
		setDataCat();
	}
	/**
	 * Getters/Setters
	 * @return
	 */
	public int getIdCat()
	{
		return idCat;
	}
	public void setIdCat(int idCat) throws Exception
	{
		this.idCat = idCat;
		setDataCat();
	}
	public CategoryJPA getDataCat()
	{
		return dataCat;
	}	
	/**
	 * Method to retrieve the category
	 * @throws Exception
	 */
	private void setDataCat() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		showCatRemote = (EAgendaFacadeRemote) ctx.lookup("java:app/pra2.jar/EAgendaFacadeBean!ejb.EAgendaFacadeRemote");
		dataCat = (CategoryJPA) showCatRemote.showCategory(idCat);
	}
}
