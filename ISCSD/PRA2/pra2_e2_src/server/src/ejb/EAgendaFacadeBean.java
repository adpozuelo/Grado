package ejb;
/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import jpa.CategoryJPA;
import ejb.EAgendaFacadeRemote;
/**
 * EJB Session Bean Class of ISCSD - PRA2"
 */
@Stateless
public class EAgendaFacadeBean implements EAgendaFacadeRemote {
	//Persistence Unit Context
	@PersistenceContext(unitName="pra2") 
	private EntityManager entman;
	/**
	 * Method that returns Collection of all categories
	 */
	public java.util.Collection<CategoryJPA> listAllCategories() {
		@SuppressWarnings("unchecked")
		Collection<CategoryJPA> allCategories = entman.createQuery("from CategoryJPA").getResultList();
		return allCategories;
	}
	/**
	 * Method that returns instance of the class category
	 */
	public CategoryJPA showCategory(int categoryId)throws PersistenceException {
		CategoryJPA cat = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<CategoryJPA> cats = entman.createQuery("FROM CategoryJPA b WHERE b.id = ?1").setParameter(1, new Integer(categoryId)).getResultList();
			if (!cats.isEmpty() || cats.size()==1)
			{
				Iterator<CategoryJPA> iter =cats.iterator();
				cat = (CategoryJPA) iter.next();				
			}
		}catch (PersistenceException e) {
			System.out.println(e);
		} 
	    return cat;
	}
	/**
	 * Method that update a category and returns it with chages are made
	 */
	public CategoryJPA updateCategory(int id, String name, String description){
		CategoryJPA cat = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<CategoryJPA> cats = entman.createQuery("FROM CategoryJPA b WHERE b.id = ?1").setParameter(1, new Integer(id)).getResultList();
			if (!cats.isEmpty() || cats.size()==1)
			{
				Iterator<CategoryJPA> iter =cats.iterator();
				cat = (CategoryJPA) iter.next();				
			}
			
		}catch (PersistenceException e) {
			System.out.println(e);
		}
		entman.persist(cat); // persist category
		cat.setName(name); // make name update
		cat.setDescription(description); // make description update
		return cat; // return updated category
	}
	/**
	 * Method that add a category in the system/database
	 */
	  public void addCategory(String name, String description){
		  int numCategories = numCategories(); // get the number of categories in the system/database
		  // Create new category with correct serial id, name and description
		  CategoryJPA cat = new CategoryJPA(numCategories + 1, name, description);
		  entman.persist(cat); // persist category in the database.
	  }
	  /**
	   * (Private) Method that returns the number of categories in the system/database
	   * @return
	   */
	  private int numCategories() {
			@SuppressWarnings("unchecked")
			Collection<CategoryJPA> allCategories = entman.createQuery("from CategoryJPA").getResultList();
			return allCategories.size();
		}
}