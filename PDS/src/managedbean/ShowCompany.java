/**
 * ShowCompany managed bean class.
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
import jpa.Company;
import ejb.CompanyFacadeRemote;

/**
 * Managed Bean ShowCompany
 */
@ManagedBean(name = "companyshow")
@SessionScoped
public class ShowCompany implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private CompanyFacadeRemote companyFacade;

	protected Company dataCompany;
	protected int idCompany = 1;
	
	public ShowCompany() throws Exception 
	{
		setDataCompany();
	}
	
	/**
	 * Get/set the id number and Company instance
	 * @return Company Id
	 */
	public int getIdCompany()
	{
		return idCompany;
	}
	public void setIdCompany(int idCompany) throws Exception
	{
		this.idCompany = idCompany;
		setDataCompany();
	}
	
	/**
	 * Get/set the Company data
	 * @return Category Id
	 */
	public Company getDataCompany()
	{
		return dataCompany;
	}	
	public void setDataCompany() throws Exception
	{	
		Properties props = System.getProperties();
		Context ctx = new InitialContext(props);
		companyFacade = (CompanyFacadeRemote) ctx.lookup("java:app/eAgenda.jar/CompanyFacadeBean!ejb.CompanyFacadeRemote");
		dataCompany = (Company) companyFacade.showCompany(idCompany);
	}
}