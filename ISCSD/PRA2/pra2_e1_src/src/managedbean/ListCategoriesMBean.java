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
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "categories")
@SessionScoped
public class ListCategoriesMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private EAgendaFacadeRemote listAllCatRemote; // EAgenda bussiness facade
	private Collection<CategoryJPA> categoryList; // List of categories
	/**
	 * Contructors
	 * @throws Exception
	 */
	public ListCategoriesMBean() throws Exception
	{
		this.categoryList(); // Call to private initialized category list method
	}
	/**
	 * Getters/Setters
	 * @return
	 * 		
	 */
	public Collection<CategoryJPA> getCategoryList()
	{
		return this.categoryList;
	}
	/**
	 * Method to initialize the list of categories
	 * @throws Exception
	 */
	private void categoryList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		listAllCatRemote = (EAgendaFacadeRemote) ctx.lookup("java:app/pra2.jar/EAgendaFacadeBean!ejb.EAgendaFacadeRemote");
		categoryList = (Collection<CategoryJPA>)listAllCatRemote.listAllCategories();
	}
}