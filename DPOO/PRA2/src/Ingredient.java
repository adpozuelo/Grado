import java.util.ArrayList;
import java.util.List;

/**
 * Ingredient class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Ingredient {
    
    /**
     * ingredient's code.
     */
    private String code;
    
    /**
     * ingredient's name.
     */
    private String name;
    
    /**
     * ingredient's quantity.
     */
    private float quantity;
    
    /**
     * ingredient's unit of measure.
     */
    private String unit;
    
    /**
     * recipe's list.
     */
    private List<Recipe> lRecipes;
    
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
     */
    public Ingredient(String code, String name, float quantity, String unit) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.lRecipes = new ArrayList<Recipe>();
    }

    /**
     * Get code atribute.
     * @return
     *          code value.
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code atribute.
     * @param code
     *              ingredient's code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get name atribute.
     * @return
     *          name value.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name atribute.
     * @param name
     *              ingredient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get quantity atribute.
     * @return
     *          quantity value.
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Set quantity atribute.
     * @param quantity
     *                  ingredient's quantity.
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Get unit of measure atribute.
     * @return
     *          unit of measure value.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Set unit of measure atribute.
     * @param unit
     *              ingredient's unit of measure.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Get recipe's list.
     * @return
     *          list of recipes.
     */
    public List<Recipe> getlRecipes() {
        return lRecipes;
    }

    /**
     * Set recipe's list.
     * @param lRecipes
     *                  recipe's list.
     */
    public void setlRecipes(List<Recipe> lRecipes) {
        this.lRecipes = lRecipes;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o
     *          the reference object with which to compare.
     * @return
     *          true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return this.code.equals(((Ingredient)o).code);
    }
  
}
