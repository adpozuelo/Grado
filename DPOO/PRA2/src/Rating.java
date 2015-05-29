/**
 * Rating class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Rating {
    
    /**
     * rating's value.
     */
    private float value;
    
    /**
     * rating's gourmet.
     */
    private Gourmet gourmet;
    
    /**
     * rating's recipe.
     */
    private Recipe recipe;

    /**
     * Constructor.
     * @param gourmet
     *              rating's gourmet.
     * @param recipe
     *              rating's recipe.
     * @param value
     *              rating's value.
     */
    public Rating(Gourmet gourmet, Recipe recipe, float value) {
        this.value = value;
        this.gourmet = gourmet;
        this.recipe = recipe;
    }

    /**
     * Get value atribute.
     * @return
     *          value of value.
     */
    public float getValue() {
        return value;
    }

}
