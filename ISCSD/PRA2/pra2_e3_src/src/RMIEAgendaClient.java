/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.rmi.Naming;
public class RMIEAgendaClient {
	/**
	 * Client program for the "RMI PRA2 E3".
     * @param argv The command line arguments which are ignored.
	 */	
	@SuppressWarnings("unchecked")
	public static void main (String[] argv) {
	    try {
	      // Connect to RMI server interface with rmiregistry at 1099 port and PRA2RMI name
	      RMIEAgendaInterface pra2EAgendaInterface = (RMIEAgendaInterface)Naming.lookup("rmi://localhost:1099/PRA2RMI");
	      String c; // Input string
	      @SuppressWarnings("rawtypes")
	      boolean exit = false; // Boolean control menu
	      while (!exit)
	      {
	    	  System.out.println(""); // HEADER
	    	  System.out.println("          EAGENDA PRA2 ISCSD RMI E3");
	    	  System.out.println("          You can show category's detail");
	      	  System.out.println("");
	    	  System.out.println("          Type category's number (from 1 to 6) or 0 to exit");  
	      	  c = input(); // Type option
	      	  try 
	      	  {
	      	    if (c.equals("0")) // If type "0" exit
		      	{
	      	    	exit = true;
		      	}
	      	    // If type between 1 and 6 (inclusive) category's detail will be show
		      	else if (Integer.parseInt(String.valueOf(c))>0 & Integer.parseInt(String.valueOf(c))<=6)
		      	{
		      	    System.out.println("");
		      	    // Request category to RMI server interface
		      	   	RMICategoryTO cat = (RMICategoryTO)pra2EAgendaInterface.showCategory(Integer.parseInt(String.valueOf(c)));
		      	    System.out.println("          I N F O R M A T I O N   A B O U T   C A T");					      
	      			System.out.println("");
	      			System.out.println("          ID number      : "+cat.getId() + " "); 					   
	      			System.out.println("          Cat name       : "+cat.getName() + " "); 
	      			System.out.println("          Cat description: "+cat.getDescription() + " "); 
		      	   	System.out.println("");
		   	    	}
		      	
		      	else // Else, no valid number typed
		      	{
		      		exit = false;
		      		System.out.println("");
		      		System.out.println ("          You have to enter a valid number");
		      		System.out.println("");
		      	}
	      	  } catch (NumberFormatException e) {
	      		  System.out.println("");
	      		  System.out.println ("          You have to enter a valid number");
	      		  System.out.println("");
	      		  exit = false;    
	      	  }
		  }
	    } catch (Exception e) {
	      System.out.println ("PRA2 RMI exception: " + e);
	    }
	  }

	  /**
	   * Reads a string from keyboard
	   * @return String
	   * @throws IOException
	   */
	  static public String input() throws IOException
	  {
	        String input; 
	        //an instance of the BufferedReader class
	        //will be used to read the data
	        BufferedReader reader;
	        //specify the reader variable 
	        //to be a standard input buffer
	        reader = new BufferedReader(new InputStreamReader(System.in));
	        //read the data entered by the user using 
	        //the readLine() method of the BufferedReader class
	        //and store the value in the name variable
	        input = reader.readLine();
	        return input;
	  }
}
