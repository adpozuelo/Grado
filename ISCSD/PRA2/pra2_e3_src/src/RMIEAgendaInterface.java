/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.rmi.*;
	/**
	 * Remote Interface for the RMI ISCSD PRA2
	 */
public interface RMIEAgendaInterface extends Remote {
	/**
	   * Method that returns requested category from system/database
	   */
	  public RMICategoryTO showCategory(int catId) throws RemoteException;
}
