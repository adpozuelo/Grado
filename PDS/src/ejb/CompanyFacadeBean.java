/**
 * CompanyFacadeBean stateless session bean class.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import jpa.*;

@Stateless
public class CompanyFacadeBean implements CompanyFacade, CompanyFacadeRemote {

	@PersistenceContext(unitName="eagenda") 
	private EntityManager entman;

	/**
	 * Method that add Company
	 */
	public int addCompany(Company company) {
				
		if (existCompany(company)) {
			return 1;
		} else {
			try {
				entman.persist(company);
				return 0;
			} catch (Exception e) {
				return 2;
			}
		}
		
	}
	/**
	 * Method that check if exist a Company with the same name
	 */
	private boolean existCompany(Company company){
		try {
			int numCompaniesSomeName = (int)entman.createQuery("SELECT c.name FROM Company c WHERE c.name = ?1").setParameter(1,  company.getName()).getResultList().size();
		
			if(numCompaniesSomeName==0){
				return false;
			}else{
				return true;
			}
		}
		catch(Exception e){
			
			throw e;
		}
		
	}
	/**
	 * Method that check if exist another Company with the same name
	 */
	private boolean existCompanyUpdate(Company company){
		try {
			int numOthersCompaniesSomeName = (int)entman.createQuery("SELECT c.name FROM Company c WHERE c.name = ?1 and c.id <> ?2").setParameter(1,  company.getName()).setParameter(2, company.getId()).getResultList().size();
		
			if((numOthersCompaniesSomeName==0)){
				return false;
			}else{
				return true;
			}
		}
		catch(Exception e){
			
			throw e;
		}
		
	}
	/**
	 * Method that returns collection of all SuperUsers
	 */
	public Collection<SuperUser> listAllSuperUser() {
		
		@SuppressWarnings("unchecked")
		Collection<SuperUser> allSuperUser = (Collection<SuperUser>) entman.createQuery("from SuperUser SU WHERE SU.active = ?1").setParameter(1, true).getResultList();
		return allSuperUser;
	}

	/**
	 * Method that returns collection of all Companies
	 */
	public Collection<Company> listAllCompanies() {
		
		@SuppressWarnings("unchecked")
		Collection<Company> allCompanies = (Collection<Company>)entman.createQuery("from Company").getResultList();
		return allCompanies;
	}

	/**
	 * Method that returns instance of the class Company by Id
	 */
	public Company showCompany(int companyId) {
		Company company = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<Company> companies = entman.createQuery("FROM Company comp WHERE comp.id = ?1").setParameter(1, new Integer(companyId)).getResultList();
			if (!companies.isEmpty() || companies.size()==1)
			{
				Iterator<Company> iter = companies.iterator();
				company = (Company) iter.next();				
			}
		}catch (PersistenceException e) {
			
			throw e;
		} 
	    return company;
	}

	/**
	 * Method that update a company by id
	 */
	public int updateCompany(Company company) {
		
		try{
			
			if (!existCompanyUpdate(company)) {
				entman.merge(company);
				return 0;
			}else{
				return 1;
			}
		}catch (Exception e){
			
			return 2;
		}
	}
}