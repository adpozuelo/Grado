/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.rmi.*;
	/**
	 * Implementation for the RMI ISCSD PRA2.
	 */
public class RMIEAgendaImpl implements RMIEAgendaInterface {
	private static final long serialVersionUID = 1L;
	/**
	 * Contructors
	 */
	  protected RMIEAgendaImpl(){}
	  /**
	   * Method that returns requested category from system/database
	   */
	  public RMICategoryTO showCategory(int catId) throws RemoteException {
		   RMICategoryTO cat = new RMIEAgendaImplDAO().showCat(catId); // Request category to DAO facade
	       System.out.flush();
	       return cat; // return requested category
	  }
}
