import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * HackingCook class of the HackingCook JAVA Practice.
 * </br>Controler class.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class HackingCook {
    
    /**
     * ingredient's list.
     */
    private List<Ingredient> lIngredients;
    
    /**
     * recipe's list.
     */
    private List<Recipe> lRecipes;
    
    /**
     * speciality's list.
     */
    private List<Specialty> lSpecialties;
    
    /**
     * restaurant's list.
     */
    private List<Restaurant> lRestaurants;
    
    /**
     * cuisine's list.
     */
    private List<Cuisine> lCuisines;
    
    /**
     * gourmet's list.
     */
    private List<Gourmet> lGourmets;
    
    /**
     * date
     * @deprecated 
     */
    private SimpleDateFormat sdf;
    
    /**
     * line separator for all operating systems.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Constructor.
     */
    public HackingCook() {
        lIngredients = new ArrayList<Ingredient>();
        lRecipes = new ArrayList<Recipe>();
        lSpecialties = new ArrayList<Specialty>();
        lRestaurants = new ArrayList<Restaurant>();
        lCuisines = new ArrayList<Cuisine>();
        lGourmets = new ArrayList<Gourmet>();
        sdf = null;
    }

    /**
     * Add ingredient to ingredient's list of HackingCook.
     * @param code
     *              ingredient's code.
     * @param name
     *              ingredient's name.
     * @param quantity
     *              ingredient's quantity.
     * @param unit
     *              ingredient's unit of measure.
     * @throws HackingCookException
     *                              in case of null parameters or quantity == 0.
     * @throws HackingCookException
     *                              in case of list contains the ingredient.
     */
    public void addIngredient(String code, String name, float quantity, String unit) throws HackingCookException {
        if (code == null || name == null || quantity == 0.0f || unit == null )
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lIngredients.contains(new Ingredient(code, name, quantity, unit)))
            throw new HackingCookException(HackingCookException.INGREDIENT_ALREADY_EXISTS);
        else
            lIngredients.add(new Ingredient(code, name, quantity, unit));
    }

    /**
     * Add prepared ingredient to ingredient's list of HackingCook.
     * @param code
     *              ingredient's code.
     * @param name
     *              ingredient's name.
     * @param quantity
     *              ingredient's quantity.
     * @param unit
     *              ingredient's unit of measure.
     * @param cookingTime
     *                  ingredient's cooking time.
     * @throws HackingCookException
     *                              in case of null parameters or quantity == 0 or cookingTime == 0.
     * @throws HackingCookException
     *                              in case of list contains the ingredient.     * 
     */
    public void addIngredient(String code, String name, float quantity, String unit, int cookingTime) throws HackingCookException {
        if (code == null || name == null || quantity == 0.0f || unit == null || cookingTime == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lIngredients.contains(new PreparedIngredient(code, name, quantity, unit, cookingTime)))
            throw new HackingCookException(HackingCookException.INGREDIENT_ALREADY_EXISTS);
        else
            lIngredients.add(new PreparedIngredient(code, name, quantity, unit, cookingTime));
    }

    /**
     * Add recipe to recipe's list of HackingCook.
     * @param code
     *              recipe's code.
     * @param name
     *              recipe's name.
     * @param preparationMode
     *                      recipe's preparation mode.
     * @throws HackingCookException
     *                              in case of null parameters or code == 0.
     * @throws HackingCookException
     *                              in case of list contains the recipe.
     */
    public void addRecipe(int code, String name, String preparationMode) throws HackingCookException {
        if (code == 0 || name == null || preparationMode == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lRecipes.contains(new Recipe(code, name, preparationMode)))
            throw new HackingCookException(HackingCookException.RECIPE_ALREADY_EXISTS);
        else
            lRecipes.add(new Recipe(code, name, preparationMode));
    }

    /**
     * Add ingredient to recipe.
     * @param ingredientCode
     *                      ingredient's code.
     * @param recipeCode
     *                  recipe's code.
     * @throws HackingCookException
     *                              in case of null parameters or recipeCode == 0.
     * @throws HackingCookException
     *                              in case of ingredient doesn't exist in list of ingredients.
     * @throws HackingCookException
     *                              in case of recipe doesn't exist in list of recipes.
     * @see #findIngredientByCode(java.lang.String) 
     */
    public void addIngredientToRecipe(String ingredientCode, int recipeCode) throws HackingCookException {
        if (ingredientCode == null || recipeCode == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lIngredients.contains(new Ingredient(ingredientCode, "", 0.0f, "")))
            throw new HackingCookException(HackingCookException.INGREDIENT_DOES_NOT_EXIST);
        if (!lRecipes.contains(new Recipe(recipeCode, "", "")))
            throw new HackingCookException(HackingCookException.RECIPE_DOES_NOT_EXIST);
        Ingredient ingredient = findIngredientByCode(ingredientCode);
        Recipe recipe = findRecipeByCode(recipeCode);
        recipe.addIngredient(ingredient);
    }
    
    /**
     * Add specialty to specialty's list of HackingCook.
     * @param code
     *              specialty's code
     * @param name
     *              specialty's name
     * @throws HackingCookException
     *                              in case of null parameters or code == 0.
     * @throws HackingCookException
     *                              in case of list contains the specialty.
     */
    public void addSpecialty(int code, String name) throws HackingCookException {
        if (code == 0 || name == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lSpecialties.contains(new Specialty(code, name)))
            throw new HackingCookException(HackingCookException.SPECIALITY_ALREADY_EXISTS);
        else
            lSpecialties.add(new Specialty(code, name));
    }

    /**
     * Link recipe to specialty.
     * @param recipeId
     *                  recipe's code.
     * @param specialtyId
     *                      specialty's code.
     * @throws HackingCookException
     *                              in case of recipeId == 0 or specialtyId == 0.
     * @throws HackingCookException
     *                              in case of recipe doesn't exist in list of recipes.
     * @throws HackingCookException
     *                              in case of specialty doesn't exist in list of specialties.
     * @see #findRecipeByCode(int) 
     * @see #findSpecialtyByCode(int) 
     */
    public void linkRecipeToSpecialty(int recipeId, int specialtyId) throws HackingCookException {
        if (recipeId == 0 || specialtyId == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lRecipes.contains(new Recipe(recipeId, "", "")))
            throw new HackingCookException(HackingCookException.RECIPE_DOES_NOT_EXIST);
        if (!lSpecialties.contains(new Specialty(specialtyId, "")))
            throw new HackingCookException(HackingCookException.SPECIALITY_DOES_NOT_EXIST);
        Recipe recipe = findRecipeByCode(recipeId);
        Specialty specialty = findSpecialtyByCode(specialtyId);
        recipe.addSpeciality(specialty);
    }

    /**
     * Add restaurant to restaurant's list of HackingCook.
     * @param code
     *              restaurant's code.
     * @param name
     *              restaurant's name.
     * @param postalCode
     *              restaurant's postal code.
     * @throws HackingCookException
     *                              in case of null parameters or code == 0.
     * @throws HackingCookException
     *                              in case of list contains the restaurant.
     */
    public void addRestaurant(int code, String name, String postalCode) throws HackingCookException {
        if (code == 0 || name == null || postalCode == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lRestaurants.contains(new Restaurant(code, name, postalCode)))
            throw new HackingCookException(HackingCookException.RESTAURANT_ALREADY_EXISTS);
        else
            lRestaurants.add(new Restaurant(code, name, postalCode));
    }

    /**
     * Link recipe to restaurant.
     * @param recipeId
     *                  recipe's code.
     * @param restaurantId
     *                      restaurant's code.
     * @throws HackingCookException
     *                              in case of recipeId == 0 or restaurantId == 0.
     * @throws HackingCookException
     *                              in case of recipe doesn't exist in list of recipes.
     * @throws HackingCookException
     *                              in case of restaurant doesn't exist in list of restaurants.
     * @see #findRecipeByCode(int) 
     * @see #findRestaurantByCode(int) 
     */
    public void linkRecipeToRestaurant(int recipeId, int restaurantId) throws HackingCookException {
        if (recipeId == 0 || restaurantId == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lRecipes.contains(new Recipe(recipeId, "", "")))
            throw new HackingCookException(HackingCookException.RECIPE_DOES_NOT_EXIST);
        if (!lRestaurants.contains(new Restaurant(restaurantId, "", "")))
            throw new HackingCookException(HackingCookException.RESTAURANT_DOES_NOT_EXIST);
        Recipe recipe = findRecipeByCode(recipeId);
        Restaurant restaurant = findRestaurantByCode(restaurantId);
        restaurant.addRecipe(recipe);
    }

    /**
     * List recipes of restaurant.
     * @param restaurantId
     *                      restaurant's code.
     * @return
     *          list of restaurant's recipes in alphabetical order.
     * @throws HackingCookException
     *                              in case of restaurantId == 0.
     * @throws HackingCookException
     *                              in case of restaurant doesn't exist in list of restaurants.
     * @see #findRestaurantByCode(int) 
     */
    public String listRecipesOfRestaurant(int restaurantId) throws HackingCookException {
        if (restaurantId == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lRestaurants.contains(new Restaurant(restaurantId, "", "")))
            throw new HackingCookException(HackingCookException.RESTAURANT_DOES_NOT_EXIST);
        Restaurant restaurant = findRestaurantByCode(restaurantId);
        String salida = restaurant.listRecipes();
        return salida;
    }

    /**
     * Add cuisine to cuisine's list of HackingCook.
     * @param code
     *              cuisine's code.
     * @param name
     *              cuisine's name.
     * @param codeBelongTo
     *                      cuisine's belongTo.
     * @throws HackingCookException
     *                              in case of null parameters.
     * @throws HackingCookException
     *                              in case of list contains the cuisine.
     */
    public void addCuisine(String code, String name, String codeBelongTo) throws HackingCookException {
        if (code == null || name == null || codeBelongTo == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        Cuisine cuisine = new Cuisine(code, name);
        cuisine.setBelongTo(codeBelongTo);
        if (lCuisines.contains(cuisine))
            throw new HackingCookException(HackingCookException.CUISINE_ALREADY_EXISTS);
        else
            lCuisines.add(cuisine);
    }

    /**
     * Link recipe to cuisine.
     * @param recipeId
     *                  recipe's code.
     * @param cuisineId
     *                  cuisine's code.
     * @throws HackingCookException
     *                              in case of null parameters or recipeId == 0.
     * @throws HackingCookException
     *                              in case of recipe doesn't exist in list of recipes.
     * @throws HackingCookException
     *                              in case of cuisine doesn't exist in list of cuisines.
     * @see #findRecipeByCode(int) 
     * @see #findCuisineByCode(java.lang.String) 
     */
    public void linkRecipeToCuisine(int recipeId, String cuisineId) throws HackingCookException {
        if (recipeId == 0 || cuisineId == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lRecipes.contains(new Recipe(recipeId, "", "")))
            throw new HackingCookException(HackingCookException.RECIPE_DOES_NOT_EXIST);
        if (!lCuisines.contains(new Cuisine(cuisineId, "")))
            throw new HackingCookException(HackingCookException.CUISINE_DOES_NOT_EXIST);
        Recipe recipe = findRecipeByCode(recipeId);
        Cuisine cuisine = findCuisineByCode(cuisineId);
        cuisine.addRecipe(recipe);
        recipe.addCuisine(cuisine);
    }

    /**
     * List recipes of a restaurant by cuisine.
     * @param restaurantId
     *                      restaurant's code.
     * @param cuisineId
     *                  cuisine's code.
     * @return
     *          list of recipes of a restaurant by cuisine in alphabetical order.
     * @throws HackingCookException
     *                              in case of null parameters or restaurantId == 0.
     * @throws HackingCookException
     *                              in case of restaurant doesn't exist in list of restaurants.
     * @throws HackingCookException
     *                              in case of cuisine doesn't exist in list of cuisines.
     * @see #findCuisineByCode(java.lang.String) 
     * @see #findRestaurantByCode(int) 
     */
    public String listRecipesByCuisine(int restaurantId, String cuisineId) throws HackingCookException {
        if (restaurantId == 0 || cuisineId == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lRestaurants.contains(new Restaurant(restaurantId, "", "")))
            throw new HackingCookException(HackingCookException.RESTAURANT_DOES_NOT_EXIST);
        if (!lCuisines.contains(new Cuisine(cuisineId, "")))
            throw new HackingCookException(HackingCookException.CUISINE_DOES_NOT_EXIST);
        Cuisine cuisine = findCuisineByCode(cuisineId);
        Restaurant restaurant = findRestaurantByCode(restaurantId);
        String salida = restaurant.listRecipesByCuisine(cuisine);
        return salida;
    }

    /**
     * Add gourmet to gourmet's list of HackingCook
     * @param code
     *              gourmet's code.
     * @throws HackingCookException
     *                              in case of null parameters.
     * @throws HackingCookException
     *                              in case of list contains the gourmet.
     */
    public void addGorumet(String code) throws HackingCookException {
        if (code == null)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (lGourmets.contains(new Gourmet(code)))
            throw new HackingCookException(HackingCookException.GOURMET_ALREADY_EXISTS);
        else
            lGourmets.add(new Gourmet(code));
    }

    /**
     * Link gourmet to restaurant.
     * @param gourmetId
     *                  gourmet's code.
     * @param restaurantId
     *                      restaurant's code.
     * @throws HackingCookException
     *                              in case of null parameters or restaurantId == 0.
     * @throws HackingCookException
     *                              in case of gourmet doesn't exist in list of gourmets.
     * @throws HackingCookException
     *                              in case of restaurant doesn't exist in list of restaurants.
     * @see #findGourmetByCode(java.lang.String) 
     * @see #findRestaurantByCode(int) 
     */
    public void linkGourmetToRestaurant(String gourmetId, int restaurantId) throws HackingCookException {
        if (gourmetId == null || restaurantId == 0)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lGourmets.contains(new Gourmet(gourmetId)))
            throw new HackingCookException(HackingCookException.GOURMET_DOES_NOT_EXIST);
        if (!lRestaurants.contains(new Restaurant(restaurantId, "", "")))
            throw new HackingCookException(HackingCookException.RESTAURANT_DOES_NOT_EXIST);
        Restaurant restaurant = findRestaurantByCode(restaurantId);
        Gourmet gourmet = findGourmetByCode(gourmetId);
        gourmet.addRestaurant(restaurant);
        restaurant.addGourmet(gourmet);
    }

    
    /**
     * Rate recipe by a gourmet.
     * @param gourmetId
     *                  gourmet's code.
     * @param recipeId
     *                  recipe's code.
     * @param rate
     *              rate value.
     * @throws HackingCookException
     *                              in case of null parameters or recipeId == 0 or rate == 0.
     * @throws HackingCookException
     *                              in case of gourmet doesn't exist in list of gourmets.
     * @throws HackingCookException
     *                              in case of recipe doesn't exist in list of recipes.
     * @see #findGourmetByCode(java.lang.String) 
     * @see #findRecipeByCode(int) 
     */
    public void rate(String gourmetId, int recipeId, float rate) throws HackingCookException {
        if (gourmetId == null || recipeId == 0 || rate == 0.0f)
            throw new HackingCookException(HackingCookException.NULL_ARGUMENT);
        if (!lGourmets.contains(new Gourmet(gourmetId)))
            throw new HackingCookException(HackingCookException.GOURMET_DOES_NOT_EXIST);
        if (!lRecipes.contains(new Recipe(recipeId, "", "")))
            throw new HackingCookException(HackingCookException.RECIPE_DOES_NOT_EXIST);
        Gourmet gourmet = findGourmetByCode(gourmetId);
        Recipe recipe = findRecipeByCode(recipeId);
        gourmet.rate(recipe, rate);  
    }
    
    /**
     * List best recipes.
     * @return
     *          list of best recipes ordered by recipe's mean of ratings.
     */
    public String listBestRecipes() {
        Collections.sort(lRecipes, new RateComparator());
        String salida = "";
        Iterator<Recipe> itRecipe = lRecipes.iterator();
        Recipe recipeTmp;
        while (itRecipe.hasNext()) {
            recipeTmp = itRecipe.next();
            salida += recipeTmp.getName() + " --> "+ recipeTmp.getMean() * 10 + NL;
        }
        return salida;
    }
    
    /**
     * Find a recipe by code in the list of recipes.
     * @param recipeCode
     *                  recipe's code.
     * @return 
     *          the recipe if it exists; null otherwise.
     */
    private Recipe findRecipeByCode(int recipeCode) {
        Iterator<Recipe> itRecipe = lRecipes.iterator();
        Recipe recipeTmp;
        Recipe recipe = null;
        while (itRecipe.hasNext()) {
            recipeTmp = itRecipe.next();
            if (recipeTmp.getCode() == recipeCode)
                recipe = recipeTmp;
        }
        return recipe;
    }
    
    /**
     * Find a ingredient by code in the list of ingredients.
     * @param ingredientCode
     *                      ingredient's code.
     * @return 
     *          the ingredient if it exists; null otherwise.
     */
    private Ingredient findIngredientByCode(String ingredientCode) {
        Iterator<Ingredient> itIngredient = lIngredients.iterator();
        Ingredient ingredientTmp;
        Ingredient ingredient = null;
        while (itIngredient.hasNext()) {
            ingredientTmp = itIngredient.next();
            if (ingredientTmp.getCode().equals(ingredientCode))
                ingredient = ingredientTmp;
        }
        return ingredient;
    }
    
    /**
     * Find a specialty by code in the list of specialties.
     * @param specialtyId
     *                      specialty's code.
     * @return 
     *          the specialty if it exists; null otherwise.
     */
    private Specialty findSpecialtyByCode(int specialtyId) {
        Iterator<Specialty> itSpecialty = lSpecialties.iterator();
        Specialty specialtyTmp;
        Specialty specialty = null;
        while (itSpecialty.hasNext()) {
            specialtyTmp = itSpecialty.next();
            if (specialtyTmp.getCode() == specialtyId) {
                specialty = specialtyTmp;
            }
        }
        return specialty;
    }
    
    /**
     * Find a restaurant by code in the list of restaurants.
     * @param restaurantId
     *                      restaurant's code.
     * @return 
     *          the restaurant if it exists; null otherwise.
     */
    private Restaurant findRestaurantByCode(int restaurantId) {
        Iterator<Restaurant> itRestaurant = lRestaurants.iterator();
        Restaurant restaurantTmp;
        Restaurant restaurant = null;
        while (itRestaurant.hasNext()) {
            restaurantTmp = itRestaurant.next();
            if (restaurantTmp.getCode() == restaurantId) {
                restaurant = restaurantTmp;
            }
        }
        return restaurant;
    }
    
    /**
     * Find a cuisine by code in the list of cuisines.
     * @param cuisineId
     *                  cuisine's code.
     * @return 
     *          the cuisine if it exists; null otherwise.
     */
    private Cuisine findCuisineByCode(String cuisineId) {
        Iterator<Cuisine> itCuisine = lCuisines.iterator();
        Cuisine cuisineTmp;
        Cuisine cuisine = null;
        while (itCuisine.hasNext()) {
            cuisineTmp = itCuisine.next();
            if (cuisineTmp.getCode().equals(cuisineId))
                cuisine = cuisineTmp;
        }
        return cuisine;
    }
    
    /**
     * Find a gourmet by code in the list of gourmets.
     * @param gourmetId
     *                  gourmet's code.
     * @return 
     *          the gourmet if it exists; null otherwise.
     */
    private Gourmet findGourmetByCode(String gourmetId) {
        Iterator<Gourmet> itGourmet = lGourmets.iterator();
        Gourmet gourmetTmp;
        Gourmet gourmet = null;
        while (itGourmet.hasNext()) {
            gourmetTmp = itGourmet.next();
            if (gourmetTmp.getCode().equals(gourmetId))
                gourmet = gourmetTmp;
        }
        return gourmet;
    }
    
}
