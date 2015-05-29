/**
 * CompanyFacade local interface.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Collection;
import javax.ejb.Local;
import jpa.Company;
import jpa.SuperUser;

@Local
public interface CompanyFacade {
	
	public int addCompany(Company company);
	public Collection<SuperUser> listAllSuperUser();
	public Collection<Company> listAllCompanies();
	public Company showCompany(int companyId);
	public int updateCompany(Company company);
}
