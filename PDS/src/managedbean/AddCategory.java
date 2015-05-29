/**
 * addCategory managed bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.Category;
import jpa.SuperUser;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean AddCategory
 */
@ManagedBean(name = "addcategory")
@SessionScoped
public class AddCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CategoryFacadeRemote categoryFacade;	

	private String name = new String();
	private String description = new String();
	private String nif = new String();
	protected Collection<SelectItem> superUserList = new ArrayList<SelectItem>();

	/**
	 * Method that add Category
	 */
	public String addCategory() throws Exception {
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categoryFacade = (CategoryFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");

		Category category = new Category();

		category.setName(name);
		if(description==""){
			
			description=null;
		}
		category.setDescription(description);
		
		SuperUser admin=new SuperUser();
		admin.setNif(nif);
		
		
		category.setSuperuser(admin);
		int sucess = categoryFacade.addCategory(category);
		
		if (sucess == 0) {
			return "/confirms/categoryCreatedView.xhtml";
			} else if (sucess == 1) {
				return "/errors/categoryExistErrorView.xhtml";
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
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
}