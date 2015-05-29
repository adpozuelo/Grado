/**
 * CategoryFacadeBean stateless session bean class.
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
public class CategoryFacadeBean implements CategoryFacade, CategoryFacadeRemote {

	@PersistenceContext(unitName="eagenda") 
	private EntityManager entman;

	/**
	 * Method that add Category
	 */
	public int addCategory(Category category) {
				
		if (existCategory(category)) {
			return 1;
		} else {
			try {
				entman.persist(category);
				return 0;
			} catch (Exception e) {
				return 2;
			}
		}
		
	}
	/**
	 * Method that check if exist a Category with the same name
	 */
	private boolean existCategory(Category category){
		try {
			int numCategoriesSomeName = (int)entman.createQuery("SELECT c.name FROM Category c WHERE c.name = ?1").setParameter(1,  category.getName()).getResultList().size();
		
			if((numCategoriesSomeName==0)){
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
	 * Method that check if exist another Category with the same name
	 */
	private boolean existCategoryUpdate(Category category){
		try {
			int numOthersCategoriesSomeName = (int)entman.createQuery("SELECT c.name FROM Category c WHERE c.name = ?1 and c.id <> ?2").setParameter(1,  category.getName()).setParameter(2, category.getId()).getResultList().size();
		
			if((numOthersCategoriesSomeName==0)){
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
	 * Method that returns collection of all Categories
	 */
	public Collection<Category> listAllCategories() {
		@SuppressWarnings("unchecked")
		Collection<Category> allCategories = (Collection<Category>)entman.createQuery("from Category").getResultList();
		return allCategories;
	}	
	
	/**
	 * Method that returns instance of the class Category by Id
	 */
	public Category showCategory(int categoryId) throws PersistenceException {
		Category category = null;
		try
		{
			@SuppressWarnings("unchecked")
			Collection<Category> categories = entman.createQuery("FROM Category cat WHERE cat.id = ?1").setParameter(1, new Integer(categoryId)).getResultList();
			if (!categories.isEmpty() || categories.size()==1)
			{
				Iterator<Category> iter = categories.iterator();
				category = (Category) iter.next();				
			}
		}catch (PersistenceException e) {
			
			throw e;
		} 
	    return category;
	}	
	/**
	 * Method that update a category by id
	 */
	public int updateCategory(Category category) {
		
		try{
			
			if (!existCategoryUpdate(category)) {
				
				entman.merge(category);
				return 0;
			}else{
				return 1;
			}
		}catch (Exception e){
			
			return 2;
		}
	}

}