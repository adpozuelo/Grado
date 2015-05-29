/**
 * addCompany managed bean class.
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
import jpa.Company;
import jpa.SuperUser;
import ejb.CompanyFacadeRemote;

/**
 * Managed Bean AddCompany
 */
@ManagedBean(name = "addcompany")
@SessionScoped
public class AddCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CompanyFacadeRemote companyFacade;	

	private String name = new String();
	private String nif = new String();
	
	protected Collection<SelectItem> superUserList = new ArrayList<SelectItem>();

	/**
	 * Method that add Company
	 */
	public String addCompany() throws Exception {
		
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		companyFacade = (CompanyFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");

		Company company = new Company();

		company.setName(name);
		SuperUser admin=new SuperUser();
		admin.setNif(nif);
		
		
		company.setSuperuser(admin);
		int sucess = companyFacade.addCompany(company);
		
		if (sucess == 0) {
			return "/confirms/companyCreatedView.xhtml";
			} else if (sucess == 1) {
				return "/errors/companyExistErrorView.xhtml";
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
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
}