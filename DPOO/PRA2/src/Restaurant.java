import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Restaurant class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Restaurant {
    
    /**
     * line separator for all operating systems.
     */
    private static final String NL = System.getProperty("line.separator");
    
    /**
     * restaurant's code.
     */
    private int code;
    
    /**
     * restaurant's name.
     */
    private String name;
    
    /**
     * restaurant's postal code.
     */
    private String postalCode;
    
    /**
     * restaurant's list of recipes.
     */
    private List<Recipe> lRecipes;
    
    /**
     * restaurant's list of gourmets.
     */
    private List<Gourmet> lGourmets;

    /**
     * Constructor.
     * @param code
     *              restaurant's code.
     * @param name
     *              restaurant's name.
     * @param postalCode
     *                  restaurant's postal code.
     */
    public Restaurant(int code, String name, String postalCode) {
        this.code = code;
        this.name = name;
        this.postalCode = postalCode;
        lRecipes = new ArrayList<Recipe>();
        lGourmets = new ArrayList<Gourmet>();
    }

    /**
     * Get code atribute.
     * @return
     *          code value.
     */
    public int getCode() {
        return code;
    }

    /**
     * Set code atribute.
     * @param code
     *              code value.
     */
    public void setCode(int code) {
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
     *              name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get postalCode atribute.
     * @return
     *          postalCode value.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set postalCode atribute.
     * @param postalCode
     *                  postalCode value.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    /**
     * Add recipe to restaurant's list of recipes.
     * @param recipe
     *              recipe to add.
     * @throws HackingCookException
     *                              in case of list contains the recipe.
     */
    public void addRecipe(Recipe recipe) throws HackingCookException {
        if (lRecipes.contains(recipe))
            throw new HackingCookException(HackingCookException.RECIPE_ALREADY_EXISTS);
        else
            lRecipes.add(recipe);
    }
    
    /**
     * List restaurant's recipes.
     * @return
     *          list of recipes of restaurant.
     */
    public String listRecipes() {
        String salida = NL;
        Collections.sort(lRecipes);
        Iterator<Recipe> itRecipe = this.lRecipes.iterator();
        while (itRecipe.hasNext()) {
            salida += itRecipe.next().getName() + NL; 
        }
        return salida;
    }
    
    /**
     * List restaurant's recipes by cuisine.
     * @param cuisine
     *              cuisine of recipes.
     * @return
     *          list of recipes by cuisine of restaurant.
     */
    public String listRecipesByCuisine(Cuisine cuisine) {
        String salida = NL;
        Collections.sort(lRecipes);
        Iterator<Recipe> itRecipe = lRecipes.iterator();
        Recipe recipeTmp;
        while (itRecipe.hasNext()) {
            recipeTmp = itRecipe.next();
            if (recipeTmp.containsCuisine(cuisine))
                salida += recipeTmp.getName() + NL;
        }
        return salida;
    }
    
    /**
     * Add gourmet to restaurant's list of gourmets.
     * @param gourmet
     *              gourmet to add.
     * @throws HackingCookException
     *                              in case of list contains the gourmet.
     */
    public void addGourmet(Gourmet gourmet) throws HackingCookException {
        if (lGourmets.contains(gourmet))
            throw new HackingCookException(HackingCookException.GOURMET_ALREADY_EXISTS);
        else
            lGourmets.add(gourmet);
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
        return (new Integer(this.code)).equals(new Integer(((Restaurant)o).getCode()));
    }
    
}
