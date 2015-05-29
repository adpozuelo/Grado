/**
 * CategoryFacade local interface.
 * @author clozanomac@uoc.edu
 * @version 1.0
 */
package ejb;

import java.util.Collection;
import javax.ejb.Local;
import jpa.Category;
import jpa.SuperUser;

@Local
public interface CategoryFacade {
    public int addCategory(Category category);
    public Collection<SuperUser> listAllSuperUser();
    public Collection<Category> listAllCategories();
    public Category showCategory(int categoryId);
    public int updateCategory(Category category);
}
