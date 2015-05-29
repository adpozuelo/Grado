/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise3.Step6;

/**
 *
 * @author adpozuelo
 */
public class Selling {
    
    private BikeModel bikeModel;  // Atributo representa la asociatividad con la clase BikeModel.
    private Customer customer;  // Atributo representa la asociatividad con la clase Customer.
    private double price;  // Atributo precio
    
    // Contructor predeterminado.
    public Selling() {}
    
    // Contructor. Utilizo "Setters".
    public Selling(BikeModel bikeModelValue, Customer customerValue, double priceValue) {
        setBikeModel(bikeModelValue);
        setCustomer(customerValue);
        setPrice(priceValue);
    }

    // Metodos "getters" y "setters"
    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
