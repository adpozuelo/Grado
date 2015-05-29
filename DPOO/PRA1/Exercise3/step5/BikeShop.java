package Exercise3.Step5;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Bike Shop.
 */
class BikeShop {

   /**
    * List of bike models registered in the shop
    */
      private List bikeModels;      
   /**
    * List of customers registered in the shop
    */
      private List customers; 
       /**
    * List of disabledcustomers registered in the shop
    */
      private List disabledcustomers;

   /**
    * Constructor of the class.
    * Sets empty the lists of bike models and customers.
    */
   public BikeShop () {
      bikeModels = new ArrayList();
      customers = new ArrayList();
      disabledcustomers = new ArrayList();
   }

   /**
    * Add a Bike model to the list of Bike models
    * 
    * @param BikeModel
    *    new Bike model
    * @return true if the operation has been suscessfully, else false.
    */
   public boolean addBikeModel(BikeModel BikeModel) {
      // Modified in relation to step 0, to avoid repetitions
      if (bikeModels.contains(BikeModel))
         return false;
      else
	return bikeModels.add(BikeModel);
   }

   /**
    * Add a customer to the list of customers
    * 
    * @param customer
    *    new customer
    * @return true if the operation has been suscessfully, else false.
    */
   public boolean addCustomer(Customer customer) {
      // Modified in relation to step 0, to avoid repetitions
      if (customers.contains(customer))
         return false;
      else
	return customers.add(customer);
   }
   
   /**
    * Add a customer to the list of customers
    * 
    * @param customer
    *    new customer
    * @return true if the operation has been suscessfully, else false.
    */
   public boolean addDisabledCustomer(DisabledCustomer disabledcustomer) {
      // Modified in relation to step 0, to avoid repetitions
      if (disabledcustomers.contains(disabledcustomer))
         return false;
      else
	return disabledcustomers.add(disabledcustomer);
   }


   /**
    * Lists the information of the registered bike models.
    * 
    */
   public void listBikeModels() {
      for (Iterator it = bikeModels.iterator(); it.hasNext();) {
         BikeModel aBikeModel = (BikeModel) it.next();
         System.out.println(aBikeModel);
      }
   }

   /**
    * Lists the information of the registered customers.
    * 
    */
   public void listCustomers() {
      for (Iterator it = customers.iterator(); it.hasNext();) {
         Customer aCustomer = (Customer) it.next();
         System.out.println(aCustomer);
      }
      
      for (Iterator it = disabledcustomers.iterator(); it.hasNext();) {
         Customer aCustomer = (Customer) it.next();
         System.out.println(aCustomer);
      }
   }

}

