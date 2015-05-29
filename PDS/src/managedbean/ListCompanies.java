/**
 * ListCompanies managed bean class.
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
import jpa.Company;
import ejb.CompanyFacadeRemote;

/**
 * Managed Bean ListCompanies
 */
@ManagedBean(name = "listcompanies")
@SessionScoped
public class ListCompanies implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@EJB
	private CompanyFacadeRemote companyFacade;	
	
	private String name = new String();
	private int id = 0;
	protected Collection<SelectItem> companyListItems = new ArrayList<SelectItem>();
	protected Collection<Company> companyList = new ArrayList<Company>();
	
	public boolean disable=true;
	
	public ListCompanies() throws Exception
	{
		this.companyList();
		this.companyListItems();
	}
	
	/**
	 * Method get which return Company's items Collection
	 * @return Collection
	 */
	public Collection<SelectItem> getCompanyListItems()
	{
		return companyListItems;
	}
	
	/**
	 * Method get which return Companies Collection
	 * @return Collection
	 */
	public Collection<Company> getCompanyList()
	{
		return companyList;
	}
	
	/**
	 * Method that takes a collection of instances of Company calling 
	 * method listAllCompanies of the EJB Session
	 * @throws Exception
	 */
	public void companyList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		companyFacade = (CompanyFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");
		
		companyList = (Collection<Company>) companyFacade.listAllCompanies();

	}
	
	/**
	 * Method that takes a collection of instances of Company item calling 
	 * @throws Exception
	 */
	public void companyListItems() throws Exception
	{			
		Collection<Company> companyCollection = companyList;
		for (Iterator<Company> iter2 = companyCollection.iterator(); iter2.hasNext();)
		{
			Company comp = (Company) iter2.next();
			
			SelectItem item = new SelectItem(comp.getId(),comp.getName());
			
			this.companyListItems.add(item);			
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