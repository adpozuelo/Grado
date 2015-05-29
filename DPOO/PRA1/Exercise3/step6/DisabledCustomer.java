package Exercise3.Step6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a Disabled Customer.
 */
class DisabledCustomer extends Customer{

   /**
    * Number disabeld card
    */
      private String nbrCard;      
   
      /**
    * List of records of this DisabledCustomer
    */
      private List records;
      
   /**
    * Constructor of the class.
    * Sets the value of its attributes.
       * 
       * @param name
       * @param dni
       * @param nbrCard 
       */
      public DisabledCustomer (String name, int dni,String nbrCard) {
      super(name,dni);
      this.nbrCard=nbrCard;
       records = new ArrayList();
     
   }
      
      /**
       * 
       * @param record 
       */
      public void addRecord(Record record){
          this.getRecords().add(record);
      }

   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return (super.toString()+";  nbrCard: " + this.getNbrCard());
   }
   
   /**
    * Method to compare two Customer instances (redefinition of equals()).
    * 
    * @return true if both instances have the same value of attribute dni
    *         false otherwise.
    * 
    */
   public boolean equals(Object o) {
      return (this.getDni()==((Customer) o).getDni()) && (this.getNbrCard()==((DisabledCustomer) o).getNbrCard());
   }

    /**
     * @return the nbrCard
     */
    public String getNbrCard() {
        return nbrCard;
    }

    /**
     * @return the records
     */
    public List getRecords() {
        return records;
    }
    
    // Metodo listRecords que lista los records.
    public void listRecords() {
        String salida = this.toString();  // Creo un String para la salida.
        Iterator<Record> it = records.iterator();  // Creo un iterador para recorrer la lista.
        while (it.hasNext()) {  // Mientras la lista tenga elementos.
            Record e = it.next();  // Creo un objeto Record para asignarle el elemento actual.
            salida += BikeShop.NL + "  Percentage: " + e.getPercentage() + "; Date: " + e.getDate();  // Creo la salida segun el enunciado.
        }
        System.out.println(salida);  // Imprimo por pantalla la salida.
    }

}

