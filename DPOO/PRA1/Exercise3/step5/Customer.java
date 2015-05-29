package Exercise3.Step5;
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
    * Constructor of the class.
    * Sets the value of its attributes.
    */
   public Customer (String name, int dni) {
      this.name=name;
      this.dni=dni;
   }

   // Metodo sobreescrito equals.
   public boolean equals(Object obj) {
       if (this.getDni() == ((Customer)obj).getDni())  // Si el dni es igual en los dos Customer
           return true;  // Devuelvo true
       else  // En caso contrario.
           return false;  // Devuelvo false.
   }
   
   // Metodo "Getter" necesario.
   public int getDni() {
       return this.dni;
   }
   
   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return ("Name: " + this.name +";  DNI: " + this.dni);
   }

}

