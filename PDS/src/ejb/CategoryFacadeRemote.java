/**
 * CategoryFacadeRemote remote interface.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Collection;
import javax.ejb.Remote;
import jpa.Category;
import jpa.SuperUser;

@Remote
public interface CategoryFacadeRemote {
	
	public int addCategory(Category category);
	public Collection<SuperUser> listAllSuperUser();
	public Collection<Category> listAllCategories();
	public Category showCategory(int categoryId);
	public int updateCategory(Category category);
}
