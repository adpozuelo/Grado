package clientSoap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Iterator;

class ClientWSJavaSoap {
    @SuppressWarnings("unchecked")
	public static void main(String args[ ]) 
    {
	    EAgendaFacadeBeanWSSoapService service = new EAgendaFacadeBeanWSSoapService();
	    EAgendaFacadeRemoteWSSoap port = service.getWSEAgendaSoapPort();
	    
	    java.util.Collection<CategoryTO> resultCat;
	
	    try {
	    	//Get a Category Collection 
	    	resultCat= port.listAllCategories();
	    	String c; 
	    	int n;
	  
	    	@SuppressWarnings("rawtypes")
	    	ArrayList nameCategory = new ArrayList();
	    	boolean exit = false;
	    	while (!exit)
	    	{
	    		n=1;
	    		Iterator<CategoryTO> catIt = resultCat.iterator(); 
	    		System.out.println("");
	    		System.out.println("          C A T E G O R I E S");
	    		System.out.println("          PRA2 based on Soap Web Service");
	    		System.out.println("");
	    		while (catIt.hasNext()) 
	    		{ 
	    			nameCategory.add(catIt.next());	    		  	
	    			System.out.println("          "+n+" - "+((CategoryTO)nameCategory.get(n-1)).getName() + " - " + ((CategoryTO)nameCategory.get(n-1)).getDescription()); 
	    			n +=1;
	    		} 
	    		System.out.println("");
	    		System.out.println("          Any key to exit ");
	    		System.out.println("");
	    		c = input();
	    		exit = true;
	    		}
	    } catch (Exception e) {
	    	System.out.println ("PRA2 Client exception: " + e);
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


