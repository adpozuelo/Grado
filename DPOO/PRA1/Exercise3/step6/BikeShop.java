package Exercise3.Step6;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import Exercise3.Step6.Record;

/**
 * This class represents a Bike Shop.
 */
class BikeShop {
    
    // Campo static final para utilizar el salto de linea independientemente del SO.
    public static final String NL = System.getProperty("line.separator");

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
         DisabledCustomer aCustomer = (DisabledCustomer) it.next();
         System.out.println(aCustomer);
      }
   }
   
   /**
    * Lists the information of all sales related to an specific Bike model.
    * 
    * @param BikeModel
    *    Model of the Bike
    */
   public void listSalesByBikeModel(BikeModel bikeModel) {
       bikeModel.listSales();  // Invoco el metodo listSales de la clase BikeModel.
   }

   /**
    * Lists the information of all sales related to an specific customer.
    * 
    * @param customer
    *    Customer
    */
   public void listSalesByCustomer(Customer customer) {
       customer.listSales();  // Invoco el metodo listSales de la clase Customer.
   }
   
   /**
   * Lists the information of all records.
    * 
    */
   public void listRecordsByDisabledCustomers() {
       Iterator<DisabledCustomer> it = disabledcustomers.iterator();  // Creo un iterador para recorrer la lista.
       while (it.hasNext()) {  // Mientras existan elementos en la lista.
           it.next().listRecords();  // Llamo al metodo listRecords de la clase DisabledCustomer.
       }
   }
      
   /**
    * Make a sale
    * 
    * @param BikeModel
    *    Bike model sold
    * @param customer
    *    customer who sell the Bike
    * @param licencePlate
    *    Licence plate of the Bike
    * @param price
    *    Price of the Bike
    */
   public void sale(BikeModel bikeModel, Customer customer,double price) {
       Selling selling = new Selling(bikeModel, customer, price);  // Instancio un objeto de la clase Selling.
       bikeModel.addSales(selling);  // Invoco al metodo addSales de la clase BikeModel para añadir el objeto a la lista.
       customer.addSales(selling);  // Invoco al metodo addSales de la clase Customer para añadir el objeto a la lista.
   }
   
}

