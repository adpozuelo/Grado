import java.util.ArrayList;
import java.util.List;

/**
 * Cuisine class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Cuisine {
    
    /**
     * cuisine belongs to.
     */
    private String belongTo;
    
    /**
     * cuisine's code.
     */
    private String code;
    
    /**
     * cuisine's list of recipes.
     */
    private List<Recipe> lRecipes;
    
    /**
     * cuisine's name.
     */
    private String name;

    /**
     * Constructor.
     * @param code
     *              cuisine's code.
     * @param name
     *              cuisine's name.
     */
    public Cuisine(String code, String name) {
        this.code = code;
        this.name = name;
        lRecipes = new ArrayList<Recipe>();
        belongTo = null;
    }
    
    /**
     * Add recipe to cuisine's list of recipes.
     * @param recipe
     *              recipe to add.
     * @throws HackingCookException
     *                              in case of list contains the recipe.
     */
    public void addRecipe(Recipe recipe) throws HackingCookException {
        if (lRecipes.contains(recipe))
            throw new HackingCookException(HackingCookException.CUISINE_ALREADY_EXISTS);
        else
            lRecipes.add(recipe);
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
        return this.code.equals(((Cuisine)o).code);
    }

    /**
     * Get belongTo atribute.
     * @return
     *          belongTo value.
     */
    public String getBelongTo() {
        return belongTo;
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
     * Get name atribute.
     * @return
     *          name value.
     */
    public String getName() {
        return name;
    }

    /**
     * Set belongTo atribute.
     * @param belongTo
     *                  belongTo value.
     */
    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    /**
     * Set code atribute.
     * @param code
     *              code value.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Set name atribute.
     * @param name
     *              name value.
     */
    public void setName(String name) {
        this.name = name;
    }
   
}
