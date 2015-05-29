package Exercise3.Step6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a Customer.
 */
class Customer {

   /**
    * Name of the customer
    */
      private String name;      
   /**
    * DNI of the customer
    */
      private int dni;      
   /**
    * List of sales of this customer
    */
      private List sales;
   /**
    * Constructor of the class.
    * Sets the value of its attributes.
    */
   public Customer (String name, int dni) {
      this.name=name;
      this.dni=dni;
      sales = new ArrayList();
   }

   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return ("Name: " + this.name +";  DNI: " + this.getDni());
   }
   
   /**
    * Method to compare two Customer instances (redefinition of equals()).
    * 
    * @return true if both instances have the same value of attribute dni
    *         false otherwise.
    * 
    */
   public boolean equals(Object o) {
      return (this.getDni()==((Customer) o).getDni());
   }

    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }
     /**
    * getter method of attribute sales
    * 
    * @return a pointer to list sales
    */
   public List getSales() {
      return this.sales;
   }
   
   // Metodo addSales para añadir una venta a la lista de ventas.
   public void addSales(Selling selling) {
       sales.add(selling);  // Añado la venta a la lista.
   }
 
   // Metodo listSales para listar las ventas de BikeModel.
   public void listSales() {
       String salida = "";  // Creo un String para la salida.
       Iterator<Selling> it = sales.iterator();  // Creo un iterador para recorrer la lista.
       while (it.hasNext()) {  // Mientras existan elementos en la lista.
           Selling e = it.next();  // Asigno a un objeto Selling el elemento de la lista.
           salida += "  Price: " + e.getPrice() + " € -> corresponding to Bike model -> " + e.getBikeModel() + BikeShop.NL; // Creo la salida con el formato especificado en el enunciado.
       }
       System.out.println(salida);  // Imprimo por pantalla la salida.
   }

}

