package ejb;
/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.util.Collection;
import javax.ejb.Remote;
import jpa.CategoryJPA;
/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface EAgendaFacadeRemote {
	  /**
	   * Remotely invoked method.
	   */
	  public Collection<CategoryJPA> listAllCategories();
	  public CategoryJPA showCategory(int categoryId);
	  public CategoryJPA updateCategory(int id, String name, String description);
	  public void addCategory(String name, String description);
}