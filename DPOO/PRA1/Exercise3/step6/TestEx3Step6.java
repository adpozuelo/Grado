package Exercise3.Step6;

import Exercise3.Step6.Customer;
import Exercise3.Step6.DisabledCustomer;
import java.util.Calendar;
import java.util.Date;

/**
 * Main class used to test
 */
public class TestEx3Step6{

   /**
    * Main method
    */
   public static void main (String []args) {

      // Creating a BikeShop object
      // -------------------------
      BikeShop cs = new BikeShop();

      
     

      // Creating BikeModel objects and adding them to the BikeModels list of object cs
      // ----------------------------------------------------------------------------
      BikeModel BikeModelObj1 = new BikeModel("Madonne 4.5","Trek");
      cs.addBikeModel(BikeModelObj1);
      BikeModel BikeModelObj2 = new BikeModel("Madonne 3.2","Trek");
      cs.addBikeModel(BikeModelObj2);
      BikeModel BikeModelObj3 = new BikeModel("London","Brompthon");
      cs.addBikeModel(BikeModelObj3);
      BikeModel BikeModelObj4 = new BikeModel("London","Brompthon");  // repeated object <----
      cs.addBikeModel(BikeModelObj4);

      // Creating Customer objects and adding them to the customers list of object cs
      // ----------------------------------------------------------------------------
      Customer customerObj1 = new Customer("Juan Arias Salgado",11111111);
      cs.addCustomer(customerObj1);
      Customer customerObj2 = new Customer("Ana Maria Sanchez Ferlosio",22222222);
      cs.addCustomer(customerObj2);
      Customer customerObj3 = new Customer("Antonio Lopez de Ayala",33333333);
      cs.addCustomer(customerObj3);
   
      // Creating DisabledCustomer objects and adding them to the customers list of object cs
      // ----------------------------------------------------------------------------
      DisabledCustomer disabledcustomerObj1 = new DisabledCustomer("Cristina Gil Marin",44444444,"4444W");
      cs.addDisabledCustomer(disabledcustomerObj1);
      DisabledCustomer disabledcustomerObj2 = new DisabledCustomer("AnaMari Sanchez Ferlosio",22222222,"2222W"); 
      cs.addDisabledCustomer(disabledcustomerObj2);
      DisabledCustomer disabledcustomerObj3 = new DisabledCustomer("AnaMari Sanchez Ferlosio",22222222,"2222W"); // repeated object <----
      cs.addDisabledCustomer(disabledcustomerObj3);
      
      // Listing Bike Models Information
      // ------------------------------
      System.out.println ("------------------");
      System.out.println ("List of Bike Models");
      System.out.println ("------------------");
      cs.listBikeModels();

      // Listing Customers Information
      // ------------------------------
      System.out.println ("-----------------");
      System.out.println ("List of Customers");
      System.out.println ("-----------------");
      cs.listCustomers();
      
      // Making sales <--> Creating Selling objects and making relations with the corresponding BikeModel and Customer objects
      // --------------------------------------------------------------------------------------------------------------------
      cs.sale(BikeModelObj1,customerObj1,20000);
      cs.sale(BikeModelObj1,customerObj2,24000);
      cs.sale(BikeModelObj1,customerObj3,22000);
      cs.sale(BikeModelObj2,customerObj3,35000);
      cs.sale(BikeModelObj2,disabledcustomerObj3,25000);
            

      Disabled disabled1 = new Disabled("migraine");
      Disabled disabled2 = new Disabled("severe headache");
      Disabled disabled3 = new Disabled("ulcer");
      
      Calendar cal = Calendar.getInstance();
	  cal.clear();
	  cal.set(Calendar.YEAR, 2002);
	  cal.set(Calendar.MONTH, 10);  // November
	  cal.set(Calendar.DATE, 30);
      Date daterecord1 = cal.getTime(); 
        cal.clear();
	  cal.set(Calendar.YEAR, 2006);
	  cal.set(Calendar.MONTH, 10);  // November
	  cal.set(Calendar.DATE, 2);
      Date daterecord2 = cal.getTime(); 
      
      Record record1 = new Record(daterecord1,33.3);            
      Record record2 = new Record(daterecord2,20.3);
      Record record3 = new Record(daterecord2,11.3);
      record1.setDisabled(disabled1);
      record2.setDisabled(disabled2);
      record3.setDisabled(disabled2);
      
      
      disabledcustomerObj1.addRecord(record1);
      disabledcustomerObj1.addRecord(record2);
      disabledcustomerObj2.addRecord(record3);
              
      // Listing Sales by Bike Model
      // --------------------------
      System.out.println("=== Listing sales of the following Bike model --> " + BikeModelObj1);
      cs.listSalesByBikeModel(BikeModelObj1);
      System.out.println();

      // Listing Sales by Customer
      // --------------------------
      System.out.println("=== Listing sales of the following customer --> " + customerObj3);
      cs.listSalesByCustomer(customerObj3);
            
      // Listing Sales by DisabledCustomer
      // --------------------------
      System.out.println("=== Listing sales of the following disabled customer --> " + disabledcustomerObj3);
      cs.listSalesByCustomer(disabledcustomerObj3);
      
       // Listing Records by DisabledCustomer
      // --------------------------
      System.out.println("=== Listing records of the all disabled customer --> ");
      cs.listRecordsByDisabledCustomers();
      
      
   }

}
