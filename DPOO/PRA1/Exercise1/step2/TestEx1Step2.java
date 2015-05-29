package Exercise1.Step2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Main class used to test
 */
public class TestEx1Step2 {
 
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
      String[] complements1 = {"bar tape", "Road Brake Pads"};
      RaceBike aTrekBike =
          new RaceBike (1650.5, 54,manufactureDate1,complements1);    	 
      
      aTrekBike.add_Km_Dates(100, km1);
      aTrekBike.add_Km_Dates(200, km2);
            

      // Create a UrbanBike object
      System.out.println ("-- Create object UrbanBike --");
      String[] complements2 = {"bar tape", "Road Brake Pads"};
      UrbanBike aBrompthonBike =
    	  new UrbanBike (800,52, manufactureDate2,complements2);
      
      aBrompthonBike.add_Km_Dates(300, km1);
      aBrompthonBike.add_Km_Dates(400, km2);
      
      
      // Create a ElectricBike object
      System.out.println ("-- Create object ElectricBike --");       
      ElectricBike aMike1ElectricBike =
    	  new ElectricBike  (800,52, manufactureDate3,complements2,30);
      
      aMike1ElectricBike.add_Km_Dates(200, km3);
      
      // Create a ElectricBike object
      System.out.println ("-- Create object ElectricBike --");
      String[] complements3 = {"bar tape", "Road Brake Pads","Saddles"};
      ElectricBike aMike2ElectricBike =
    	  new ElectricBike  (1200,52, manufactureDate2,complements2,50);
           
      
      List bikes = new ArrayList();
      bikes.add(aTrekBike);
      bikes.add(aBrompthonBike);
      bikes.add(aMike1ElectricBike);
      bikes.add(aMike2ElectricBike);
      
    	  
	  cal.set(Calendar.YEAR, 2002);
	  cal.set(Calendar.MONTH, 10);  // November
	  cal.set(Calendar.DATE, 30);
	  Date compute1 = cal.getTime();  
	  cal.clear();
	  cal.set(Calendar.YEAR, 2006);
	  cal.set(Calendar.MONTH, 6);  // January
	  cal.set(Calendar.DATE, 17);
          Date compute2 = cal.getTime();  
        
        
     for (int i = 0;i<bikes.size();i++){
         System.out.println(bikes.get(i).toString());
         System.out.println("difficulty: "+ ((Bike)bikes.get(i)).computeDifficulty(compute1, compute2));          
      }
      
   }
}
