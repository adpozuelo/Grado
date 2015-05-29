/**
 * modifyCompany managed bean class.
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
import jpa.Company;
import ejb.CompanyFacadeRemote;

/**
 * Managed Bean ModifyCompany
 */
@ManagedBean(name = "modifycompany")
@ViewScoped
public class ModifyCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CompanyFacadeRemote companyRemote;	
	
	private Company dataCompany = new Company();
	
	/**
	 * Method that update Company
	 */
	public String modifyCompany(Company company) throws Exception
	{

		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		companyRemote = (CompanyFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");
		
		int sucess = companyRemote.updateCompany(company);
		
		if (sucess == 0) {
			return "/confirms/companyModifiedView.xhtml";
			} else if (sucess == 1) {
				return "/errors/companyExistErrorView.xhtml";
			} else if (sucess == 2) {
				return "/errors/internalServerErrorView.xhtml";
			} else {
				return "/homeView.xhtml";
			}

	}
	
	/**
	 * Method that return data Company to update
	 */
	public Company getDataCompany()
	{
		return dataCompany;
	}	
}