package Exercise3.Step5;

/**
 * This class represents a Disabled Customer.
 */
class DisabledCustomer extends Customer {

    /**
     * Number disabeld card
     */
    private String nbrCard;

    /**
     * Constructor of the class. Sets the value of its attributes.
     *
     * @param name
     * @param dni
     * @param nbrCard
     */
    public DisabledCustomer(String name, int dni, String nbrCard) {
        super(name, dni);
        this.nbrCard = nbrCard;

    }
    
    // MEtodo sobreescrito equals.
    public boolean equals(Object obj) {
        // Si el dni es igual y la NbrCard tambien.
        if ((this.getDni() == ((Customer)obj).getDni()) && (this.getNbrCard().equals(((DisabledCustomer)obj).getNbrCard())))
            return true;  // Devuelvo true.
        else  // En caso contrario
            return false;  // Devuelvo false.
    }

    // Metodo "getter" necesario.
    public String getNbrCard() {
        return this.nbrCard;
    }
    /**
     * toString method.
     *
     * @return A String containing the values of the class attributes.
     */
    public String toString() {
        return (super.toString() + ";  nbrCard: " + this.nbrCard);
    }
}
