import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Recipe class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Recipe implements Comparable<Object> {
    
    /**
     * recipe's code.
     */
    private int code;
    
    /**
     * recipe's name.
     */
    private String name;
    
    /**
     * recipe's preparation.
     */
    private String preparation;
    
    /**
     * recipe's list of specialties.
     */
    private List<Specialty> lSpecialties;
    
    /**
     * recipe's list of cuisines.
     */
    private List<Cuisine> lCuisine;
    
    /**
     * recipe's list of ratings.
     */
    private List<Rating> lRating;
    
    /**
     * recipe's list of ingredients.
     */
    private List<Ingredient> lIngredient;
    
    /**
     * recipe's mean.
     */
    private float mean;
     
    /**
     * Compares this object with the specified object for order. 
     * Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @param t
     *           the Object to be compared.
     * @return
     *          a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Object t) {
        return this.name.compareTo(((Recipe)t).name);
    }

    /**
     * Constructor.
     * @param code
     *              recipe's code.
     * @param name
     *              recipe's name.
     * @param preparation
     *                      recipe's preparation.
     */
    public Recipe(int code, String name, String preparation) {
        this.code = code;
        this.name = name;
        this.preparation = preparation;
        this.lCuisine = new ArrayList<Cuisine>();
        this.lIngredient = new ArrayList<Ingredient>();
        this.lRating = new ArrayList<Rating>();
        this.lSpecialties = new ArrayList<Specialty>();
    }
    
    /**
     * Add cuisine to recipe's list of cuisines.
     * @param cuisine
     *              cuisine to add.
     * @throws HackingCookException
     *                              in case of list contains the cuisine.
     */
    public void addCuisine(Cuisine cuisine) throws HackingCookException {
        if (lCuisine.contains(cuisine))
            throw new HackingCookException(HackingCookException.CUISINE_ALREADY_EXISTS);
        else
            this.lCuisine.add(cuisine);
    }
    
    /**
     * Add ingredient to recipe's list of ingredients.
     * @param ingredient
     *                  ingredient to add.
     * @throws HackingCookException
     *                              in case of list contains the cuisine.
     */
    public void addIngredient(Ingredient ingredient) throws HackingCookException {
        if (lIngredient.contains(ingredient))
            throw new HackingCookException(HackingCookException.INGREDIENT_ALREADY_EXISTS);
        else
            this.lIngredient.add(ingredient);
    }
    
    /**
     *  Add rate to recipe's list of ratings.
     *  </br>After add the rate, mean atribute is updated.
     * @param rating
     *              rate to add.
     */
    public void addRating(Rating rating) {
        this.lRating.add(rating);
        Iterator<Rating> itRating = this.lRating.iterator();
        Rating ratingTmp;
        float total = 0.0f;
        while (itRating.hasNext()) {
            ratingTmp = itRating.next();
            total += ratingTmp.getValue();
        }
        this.updateMeanRating(total / lRating.size());
    }
    
    /**
     * Add specialty to recipe's list of specialties.
     * @param specialty
     *                  specialty to add.
     * @throws HackingCookException
     *                              in case of list contains the specialty.
     */
    public void addSpeciality(Specialty specialty) throws HackingCookException {
        if (lSpecialties.contains(specialty))
            throw new HackingCookException(HackingCookException.SPECIALITY_ALREADY_EXISTS);
        else
            this.lSpecialties.add(specialty);
    }
    
    /**
     * Check if list of cuisines contains a cuisine.
     * @param cuisine
     *              cuisine to find.
     * @return
     *          true if list contains the cuisine; false otherwise.
     */
    public boolean containsCuisine(Cuisine cuisine) {
        if (lCuisine.contains(cuisine))
            return true;
        else
            return false;
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
        return (new Integer(this.code)).equals(new Integer(((Recipe)o).getCode()));
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
     * Get mean atribute.
     * @return
     *          mean value.
     */
    public float getMean() {
        return mean;
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
     * Get preparation atribute.
     * @return
     *          preparation value.
     */
    public String getPreparation() {
        return preparation;
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
     * Set name atribute.
     * @param name
     *              name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set preparation atribute.
     * @param preparation
     *                  preparation value.
     */
    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
    
    /**
     * Update mean atribute.
     * @param value 
     *              mean value.
     */
    private void updateMeanRating(float value) {
        this.mean = value;
    }
  
}
