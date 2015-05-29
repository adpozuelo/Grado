/**
 * modifyCategory managed bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package managedbean;

import java.io.Serializable;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import jpa.Category;
import ejb.CategoryFacadeRemote;

/**
 * Managed Bean ModifyCategory
 */
@ManagedBean(name = "modifycategory")
@ViewScoped
public class ModifyCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CategoryFacadeRemote categoryRemote;	
	
	private Category dataCategory = new Category();
	
	/**
	 * Method that update Category
	 */
	public String modifyCategory(Category category) throws Exception
	{

		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		categoryRemote = (CategoryFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CategoryFacadeBean!ejb.CategoryFacadeRemote");
		
		if(category.getDescription()==""){
			
			category.setDescription(null);
		}
		
		int sucess = categoryRemote.updateCategory(category);
		
		if (sucess == 0) {
			return "/confirms/categoryModifiedView.xhtml";
			} else if (sucess == 1) {
				return "/errors/categoryExistErrorView.xhtml";
			} else if (sucess == 2) {
				return "/errors/internalServerErrorView.xhtml";
			} else {
				return "/homeView.xhtml";
			}

	}
	
	/**
	 * Method that return data Category to update
	 */
	public Category getDataCategory()
	{
		return dataCategory;
	}	
}