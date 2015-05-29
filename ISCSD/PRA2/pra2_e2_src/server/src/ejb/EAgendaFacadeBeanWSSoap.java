/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
package ejb;
import java.util.*;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.jboss.ws.api.annotation.*;
import jpa.CategoryJPA;
import jpa.CategoryJPA;
import to.CategoryTO;
import ejb.EAgendaFacadeRemoteWSSoap;

/**
 * EJB Session Bean Class of PRA2 - E2 - WS
 */
@Stateless
@WebService(name="WSEAgendaSoap",endpointInterface="ejb.EAgendaFacadeRemoteWSSoap")
@WebContext( contextRoot = "/pra2WSSoap" )
public class EAgendaFacadeBeanWSSoap implements EAgendaFacadeRemoteWSSoap {
	
	//Persistence Unit Context
	@PersistenceContext(unitName="pra2") 
	private EntityManager entman;
   
	/**
	 * Method that returns Collection of all categories
	 */
	public java.util.Collection<CategoryTO> listAllCategories() {
		Collection<CategoryTO> allCategories = new ArrayList<CategoryTO>();
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> allCategoriesJPA = entman.createQuery("from CategoryJPA").getResultList();	
		for (Iterator<CategoryJPA> iter = allCategoriesJPA.iterator(); iter.hasNext();)
		{
			CategoryJPA cat2 = (CategoryJPA) iter.next();
			allCategories.add(new CategoryTO(cat2.getId(), cat2.getName(), cat2.getDescription()));			
		}
		//allCategories.add(new CategoryTO("ALL CATEGORIES"));
		return allCategories;
	}	
}
