/**
 * Exception class, with all the messages of HackingCook.
 * 
 * @author OOP teaching staff
 * @version 1.0
 * @since 2012
 */
public class HackingCookException extends Exception {

	/**
	 * Ingredient already exists
	 */
	public static final String INGREDIENT_ALREADY_EXISTS = "-ERROR: INGREDIENT ALREADY EXISTS";

	/**
	 * Specialty already exists
	 */
	public static final String SPECIALITY_ALREADY_EXISTS = "-ERROR: SPECIALITY ALREADY EXISTS";

	/**
	 * Recipe already exists
	 */
	public static final String RECIPE_ALREADY_EXISTS = "-ERROR: RECIPE ALREADY EXISTS";

	/**
	 * Restaurant already exists
	 */
	public static final String RESTAURANT_ALREADY_EXISTS = "-ERROR: RESTAURANT ALREADY EXISTS";

	/**
	 * Cusine already exists
	 */
	public static final String CUISINE_ALREADY_EXISTS = "-ERROR: CUISINE ALREADY EXISTS";

	/**
	 * Ingredient does not exist
	 */
	public static final String INGREDIENT_DOES_NOT_EXIST = "-ERROR: INGREDIENT DOES NOT EXIST";

	/**
	 * Recipe does not exist
	 */
	public static final String RECIPE_DOES_NOT_EXIST = "-ERROR: RECIPE DOES NOT EXIST";

	/**
	 * Specialty does not exist
	 */
	public static final String SPECIALITY_DOES_NOT_EXIST = "-ERROR: SPECIALITY DOES NOT EXIST";

	/**
	 * Restaurant does not exist
	 */
	public static final String RESTAURANT_DOES_NOT_EXIST = "-ERROR: RESTAURANT DOES NOT EXIST";

	/**
	 * Cusine does not exist
	 */
	public static final String CUISINE_DOES_NOT_EXIST = "-ERROR: CUISINE DOES NOT EXIST";

	/**
	 * Gourmet does not exist
	 */
	public static final String GOURMET_DOES_NOT_EXIST = "-ERROR: GOURMET DOES NOT EXIST";

	/**
	 * Gourmet already exists
	 */
	public static final String GOURMET_ALREADY_EXISTS = "-ERROR: GOURMET ALREADY EXISTS";

	/**
	 * Gourmet cannot rate a recipe
	 */
	public static final String GOURMET_CANNOT_RATE_THE_RECIPE = "-ERROR: GOURMET CANNOT RATE THE RECIPE ";

	/**
	 * Null argument
	 */
	public static final String NULL_ARGUMENT = "-ERROR: ARGUMENT IS NULL";

	/**
	 * Constructs a new HackingCookException exception with the specified detail
	 * message and cause.<br />
	 * Note that the detail message associated with cause is not automatically
	 * incorporated in this exception's detail message.
	 * 
	 * @param message
	 *            The detail message (which is saved for later retrieval by the
	 *            #getMessage() method).
	 * @param cause
	 *            The cause (which is saved for later retrieval by the
	 *            #getCause() method). (A null value is permitted, and indicates
	 *            that the cause is
	 */
	public HackingCookException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new HackingCookException exception with the specified detail
	 * message. The cause is not initialized, and may subsequently be
	 * initialized by a call to #initCause
	 * 
	 * @param message
	 *            The detail message. It's saved for later retrieval by the
	 *            #getMessage() method.
	 */
	public HackingCookException(String message) {
		super(message);
	}

}// HackingCookException