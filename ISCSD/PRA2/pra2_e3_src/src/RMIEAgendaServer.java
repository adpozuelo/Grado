/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
/**
 * Server for the RMI ISCSD PRA2.
 */
public class RMIEAgendaServer {
	/**
	 * Contructor
	 */
	public RMIEAgendaServer()
	{
	   	try {
    		RMIEAgendaImpl obj = new RMIEAgendaImpl(); // RMI Remote interface
    		// Create the remote object's stub.
    		RMIEAgendaInterface stub = (RMIEAgendaInterface) UnicastRemoteObject.exportObject(obj, 0);
    	    // Bind the remote object's stub in the registry
    	    Registry registry = LocateRegistry.getRegistry();
    	    registry.bind("PRA2RMI", stub);
    	    System.err.println("PRA2 RMI Server ready");
    	} catch (Exception e) {
    	    System.err.println("PRA2 RMI Server exception: " + e.toString());
    	    e.printStackTrace();
    	}		
	}
	/**
	 * Main method to start the server
	 * @param args
	 */
    public static void main(String args[]) 
    {
    	new RMIEAgendaServer(); // Call to the constructor
    }    
}
