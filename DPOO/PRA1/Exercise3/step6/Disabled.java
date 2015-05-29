package Exercise3.Step6;

/**
 * This class represents a Disabled .
 */
class Disabled{

   /**
    * Disabled name
    */
      private String name;      
   
   /**
    * Constructor of the class.
    * Sets the value of its attributes.
       * 
       * @param name
        */
      public Disabled (String name) {
      this.name=name;
     
   }

   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return ("  name: " + this.name);
   }
   
   
}

