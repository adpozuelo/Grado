package Exercise3.Step6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a Bike Model.
 */
class BikeModel {
    
    

   /**
    * Name of the Producer Company
    */
      private String producer;      
   /**
    * Name of the model
    */
      private String model;  
      /**
    * List of sales of this model
    */
      private List sales;
  

/**
    * getter method of attribute sales
    * 
    * @return a pointer to list sales
    */
   public List getSales() {
      return this.sales;
   }
   /**
    * Constructor of the class.
    * Sets the value of its attributes.
    */
   public BikeModel (String producer, String model) {
      this.producer=producer;
      this.model=model;
       sales = new ArrayList();
   }

   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return ("Producer: " + this.producer +";  Model: " + this.model);
   }
   
   /**
     * @return the producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }
   
   /**
    * Method to compare two BikeModel instances (redefinition of equals()).
    * 
    * @return true if both instances have the same value of attributes producer and model (both)
    *         false otherwise.
    * 
    */
   public boolean equals(Object o) {
      return (this.getProducer().equals(((BikeModel) o).getProducer()))&&(this.getModel().equals(((BikeModel) o).getModel()));
   }
   
   // Metodo addSales para añadir una venta a la lista de ventas.
   public void addSales(Selling selling) {
       sales.add(selling);  // Añado la venta.
   }
   
   // Metodo listSales para listar las ventas de BikeModel.
   public void listSales() {
       String salida = "";  // Creo un String para la salida.
       Iterator<Selling> it = sales.iterator();  // Creo un iterador para recorrer la lista.
       while (it.hasNext()) {  // Mientras existan elementos en la lista.
           Selling e = it.next();  // Asigno a un objeto Selling el elemento de la lista.
           salida += "  Price: " + e.getPrice() + " € -> sold to customer -> " + e.getCustomer() + BikeShop.NL;  // Creo la salida con el formato especificado en el enunciado.
       }
       System.out.println(salida);  // Imprimo por pantalla la salida.
   }

}

