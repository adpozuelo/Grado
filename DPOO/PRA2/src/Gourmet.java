import java.util.ArrayList;
import java.util.List;

/**
 * Gourmet class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Gourmet {
    
    /**
     * gourmet's code.
     */
    private String code;
    
    /**
     * gourmet's list of restaurants.
     */
    private List<Restaurant> lRestaurants;

    /**
     * Contructor.
     * @param code
     *              gourmet's code.
     */
    public Gourmet(String code) {
        this.code = code;
        lRestaurants = new ArrayList<Restaurant>();
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
     *              code value.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Add restaurant to gourmet's list of restaurants.
     * @param restaurant
     *                  restaurant to add.
     * @throws HackingCookException
     *                              in case of list contains the restaurant.
     */
    public void addRestaurant(Restaurant restaurant) throws HackingCookException {
        if (lRestaurants.contains(restaurant))
            throw new HackingCookException(HackingCookException.RESTAURANT_ALREADY_EXISTS);
        else
            lRestaurants.add(restaurant);
    }
    
    /**
     * Rate a recipe.
     * @param recipe
     *              recipe to rate.
     * @param rate
     *              rate value.
     * @throws HackingCookException
     */
    public void rate(Recipe recipe, float rate) throws HackingCookException {
        Rating rating = new Rating(this, recipe, rate);
        recipe.addRating(rating);
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
        return this.code.equals(((Gourmet)o).getCode());
    }
        
}
