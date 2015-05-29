package Exercise3.Step5;

/**
 * Main class used to test
 */
public class TestEx3Step5{

   /**
    * Main method
    */
   public static void main (String []args) {

      // Creating a BikeShop object
      // -------------------------
      BikeShop cs = new BikeShop();

      BikeModel BikeModelObj;

      // Creating BikeModel objects and adding them to the BikeModels list of object cs
      // ----------------------------------------------------------------------------
      BikeModelObj = new BikeModel("Madonne 4.5","Trek");
      cs.addBikeModel(BikeModelObj);
      BikeModelObj = new BikeModel("Madonne 3.2","Trek");
      cs.addBikeModel(BikeModelObj);
      BikeModelObj = new BikeModel("London","Brompthon");
      cs.addBikeModel(BikeModelObj);
      BikeModelObj = new BikeModel("London","Brompthon");  // repeated object <----
      cs.addBikeModel(BikeModelObj);

      Customer customerObj;

      // Creating Customer objects and adding them to the customers list of object cs
      // ----------------------------------------------------------------------------
      customerObj = new Customer("Juan Arias Salgado",11111111);
      cs.addCustomer(customerObj);
      customerObj = new Customer("Ana Maria Sanchez Ferlosio",22222222);
      cs.addCustomer(customerObj);
      customerObj = new Customer("Antonio Lopez de Ayala",33333333);
      cs.addCustomer(customerObj);
     
      DisabledCustomer disabledcustomerObj;

      // Creating DisabledCustomer objects and adding them to the customers list of object cs
      // ----------------------------------------------------------------------------
      disabledcustomerObj = new DisabledCustomer("Cristina Gil Marin",44444444,"4444W");
      cs.addDisabledCustomer(disabledcustomerObj);
      disabledcustomerObj = new DisabledCustomer("AnaMari Sanchez Ferlosio",22222222,"2222W"); 
      cs.addDisabledCustomer(disabledcustomerObj);
      disabledcustomerObj = new DisabledCustomer("AnaMari Sanchez Ferlosio",22222222,"2222W"); // repeated object <----
      cs.addDisabledCustomer(disabledcustomerObj);
      
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
   }

}
