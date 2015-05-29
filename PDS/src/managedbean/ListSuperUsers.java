/**
 * ListSuperUser managed bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.SuperUser;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean ListSuperUsers
 */
@ManagedBean(name = "listsuperusers")
@SessionScoped
public class ListSuperUsers implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CategoryFacadeRemote facade;	

	private String name = new String();
	private String surname = new String();
	private String nif = new String();
	protected Collection<SelectItem> superUserList = new ArrayList<SelectItem>();

	public ListSuperUsers() throws Exception
	{
		this.superUserList();
	}

	/**
	 * Method get which return Superuser Collection
	 * @return Collection
	 */
	public Collection<SelectItem> getSuperUserList()
	{
		return superUserList;
	}
	
	/**
	 * Method that takes a collection of instances of SuperUser's items calling 
	 * method listAllSuperUser of the EJB Session
	 * @throws Exception
	 */
	public void superUserList() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		facade = (CategoryFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		
		Collection<SuperUser> superUserCollection = (Collection<SuperUser>) facade.listAllSuperUser();
		for (Iterator<SuperUser> iter2 = superUserCollection.iterator(); iter2.hasNext();)
		{
			SuperUser admin = (SuperUser) iter2.next();
			
			String sname = (admin.getSurname()==null)?"":admin.getSurname();
			
			SelectItem item = new SelectItem(admin.getNif(), admin.getName() + " " + sname);
			
			this.superUserList.add(item);			
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
}