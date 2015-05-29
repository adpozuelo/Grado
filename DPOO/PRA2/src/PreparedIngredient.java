/**
 * PreparedIngredient class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class PreparedIngredient extends Ingredient {

    /**
     * ingredient's cooking time.
     */
    private int cookingTime;

    /**
     * Constructor.
     * @param code
     *              ingredient's code.
     * @param name
     *              ingredient's name.
     * @param quantity
     *              ingredient's quantity.
     * @param unit
     *              ingredient's unit of measure.
     * @param cookingTime
     *                      ingredient's cooking time.
     */
    public PreparedIngredient(String code, String name, float quantity, String unit, int cookingTime) {
        super(code, name, quantity, unit);
        this.cookingTime = cookingTime;
    }

    /**
     * Get cookingTime atribute.
     * @return
     *          ingredient's cooking time.
     */
    public int getCookingTime() {
        return cookingTime;
    }

    /**
     * Set cookingTime atribute.
     * @param cookingTime
     *                  ingredient's cooking time.
     */
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

}
