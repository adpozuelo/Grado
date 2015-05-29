package Exercise2.Step3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Main class used to test
 */
public class TestEx2Step3 {
 
   /**
    * Main method
    */
   public static void main (String[] args) {
	  Calendar cal = Calendar.getInstance();
	  cal.clear();
	  cal.set(Calendar.YEAR, 2002);
	  cal.set(Calendar.MONTH, 10);  // November
	  cal.set(Calendar.DATE, 30);
	  Date manufactureDate1 = cal.getTime();  
	  cal.clear();
	  cal.set(Calendar.YEAR, 2006);
	  cal.set(Calendar.MONTH, 0);  // January
	  cal.set(Calendar.DATE, 16);
	  Date manufactureDate2 = cal.getTime(); 
	  cal.clear();
	  cal.set(Calendar.YEAR, 2010);
	  cal.set(Calendar.MONTH, 5);  // June
	  cal.set(Calendar.DATE, 16);
	  Date manufactureDate3 = cal.getTime();
	  cal.clear();
	  cal.set(Calendar.YEAR, 2006);
	  cal.set(Calendar.MONTH, 5);  // June
	  cal.set(Calendar.DATE, 16);                    
          Date km1 = cal.getTime(); 	 
          cal.clear();
	  cal.set(Calendar.YEAR, 2006);
	  cal.set(Calendar.MONTH, 6);  // July
	  cal.set(Calendar.DATE, 16);                    
          Date km2 = cal.getTime(); 
          cal.clear();
	  cal.set(Calendar.YEAR, 2012);
	  cal.set(Calendar.MONTH, 2);  // March
	  cal.set(Calendar.DATE, 16);	  
	  Date km3 = cal.getTime(); 
	  
	  
	   
      // Create a TrialBike object
      System.out.println ("-- Create object RaceBike --");
      List complements1 = new ArrayList();
      complements1.add(new String("bar tape"));
      complements1.add(new String("Road Brake Pads"));
      
      RaceBike aTrekBike =
          new RaceBike (1650.5, 54,manufactureDate1,complements1);    	 
      
      
      aTrekBike.add_Km_Dates(100.0, km1);
      aTrekBike.add_Km_Dates(200.0, km2);
      
      System.out.println( aTrekBike );
      System.out.println();
        try {
            aTrekBike.addComplement("bar tape");
        } catch (BikeException ex) {
            ex.printStackTrace();

        }

      System.out.println( aTrekBike );
      System.out.println();
      

      // Create a UrbanBike object
      System.out.println ("-- Create object UrbanBike --");     
      List complements2 = new ArrayList();
      complements2.add(new String("bar tape"));
      complements2.add(new String("Road Brake Pads"));

      UrbanBike aBrompthonBike =
    	  new UrbanBike (800,52, manufactureDate2,complements2);
      
      aBrompthonBike.add_Km_Dates(300, km1);
      aBrompthonBike.add_Km_Dates(400, km2);
      
      System.out.println( aBrompthonBike );
      System.out.println();
      
      // Create a ElectricBike object
      System.out.println ("-- Create object ElectricBike --");       
      ElectricBike aMike1ElectricBike =
    	  new ElectricBike  (800,52, manufactureDate3,complements2,30);
      
      aMike1ElectricBike.add_Km_Dates(200, km3);
      
      System.out.println( aMike1ElectricBike );
      System.out.println();
      
      // Create a ElectricBike object
      System.out.println ("-- Create object ElectricBike --");     
      List complements3 = new ArrayList();
      complements3.add(new String("bar tape"));
      complements3.add(new String("Road Brake Pads"));
      complements3.add(new String("Saddles"));

      ElectricBike aMike2ElectricBike =
    	  new ElectricBike  (1200,52, manufactureDate3,complements3,50);
      System.out.println( aMike2ElectricBike );
      System.out.println();
      
   }
}