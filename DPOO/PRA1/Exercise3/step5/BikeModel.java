package Exercise3.Step5;
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
    * Constructor of the class.
    * Sets the value of its attributes.
    */
   public BikeModel (String producer, String model) {
      this.producer=producer;
      this.model=model;
   }

   // Sobreescribo el metodo equals.
   public boolean equals(Object obj) {
       // Si el modelo y el prodcutor es el mismo en los dos BikeModel
       if ((getModel().equals(((BikeModel)obj).getModel())) && (getProducer().equals(((BikeModel)obj).getProducer())))
           return true;  // Devuelvo true
       else  // En caso contrario.
           return false;  // Devuelvo fase.
       
   }
   
   
   /**
    * toString method.
    * 
    * @return A String containing the values of the class attributes.
    */
   public String toString() {
      return ("Producer: " + this.getProducer() +";  Model: " + this.getModel());
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

}

