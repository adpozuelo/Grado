/**
 * ListCategories managed bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.Category;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean ListCategories
 */
@ManagedBean(name = "listcategories")
@SessionScoped
public class ListCategories implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@EJB
	private CategoryFacadeRemote categoryFacade;
	
	private String name = new String();
	private int id = 0;	
	protected Collection<SelectItem> categoryListItems = new ArrayList<SelectItem>();
	protected Collection<Category> categoryList = new ArrayList<Category>();
	public boolean disable=true;
	
	public ListCategories() throws Exception
	{
		this.categoryList();
		this.categoryListItems();
	}

	/**
	 * Method get which return Category's items Collection
	 * @return Collection
	 */
	public Collection<SelectItem> getCategoryListItems()
	{
		return categoryListItems;
	}
	
	/**
	 * Method get which return Categories Collection
	 * @return Collection
	 */
	public Collection<Category> getCategoryList()
	{
		return categoryList;
	}

	/**
	 * Method that takes a collection of instances of Category calling 
	 * method listAllCategories of the EJB Session
	 * @throws Exception
	 */
	public void categoryList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categoryFacade = (CategoryFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		
		categoryList = (Collection<Category>) categoryFacade.listAllCategories();

	}
	
	/**
	 * Method that takes a collection of instances of Category item calling 
	 * @throws Exception
	 */
	public void categoryListItems() throws Exception
	{			
		Collection<Category> categoryCollection = categoryList;
		
		for (Iterator<Category> iter2 = categoryCollection.iterator(); iter2.hasNext();)
		{
			Category cat = (Category) iter2.next();
			
			SelectItem item = new SelectItem(cat.getId(),cat.getName());
			
			this.categoryListItems.add(item);			
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
	
	public int getId() {
		return id;
	}
	public void setName(int id) {
		this.id = id;
	}
	
}
