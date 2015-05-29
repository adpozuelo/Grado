/**
 * ShowCategory managed bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.Category;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean ShowCategory
 */
@ManagedBean(name = "categoryshow")
@SessionScoped
public class ShowCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CategoryFacadeRemote categoryFacade;

	protected Category dataCategory;
	protected int idCategory = 1;
	
	public ShowCategory() throws Exception 
	{
		setDataCategory();
	}
	
	/**
	 * Get/set the id number and Category instance
	 * @return Category Id
	 */
	public int getIdCategory()
	{
		return idCategory;
	}
	public void setIdCategory(int idCategory) throws Exception
	{
		this.idCategory = idCategory;
		setDataCategory();
	}
	
	/**
	 * Get/set the Category data
	 * @return Category Id
	 */
	public Category getDataCategory()
	{
		return dataCategory;
	}	
	public void setDataCategory() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categoryFacade = (CategoryFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		dataCategory = (Category) categoryFacade.showCategory(idCategory);
	}
}