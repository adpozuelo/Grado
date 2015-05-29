/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
package ejb;
import java.util.Collection;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

import to.CategoryTO;

/**
 * Session EJB Remote Interfaces
 */
@Remote
@WebService
public interface EAgendaFacadeRemoteWSSoap {
	  /**
	   * Remotely invoked method.
	   */
	  @WebMethod public Collection<CategoryTO> listAllCategories();
}
