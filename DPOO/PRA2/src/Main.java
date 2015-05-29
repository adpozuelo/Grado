import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Main function of the HackingCook JAVA Practice
 * 
 * @author OOP teaching staff
 * @version 1.0
 * @since 2012
 */
public class Main {

	// Generic Vars
	private static final String COMMENT_LINE = "#";
	private static final String NL = System.getProperty("line.separator");
	private static final String SCREEN = "SCREEN";
	private static final String KEYBOARD = "KEYBOARD";
	private static final String WRONG_COMMAND = "-ERROR: COMMAND DOES NOT EXIST";
	private static final String WRONG_NUMBER_OF_ARGUMENTS = "-ERROR: WRONG NUMBER OF ARGUMENTS";

	// Commands tokens
	private static final String ADD_INGREDIENT = "addIngredient";
	private static final String ADD_RECIPE = "addRecipe";
	private static final String ADD_INGREDIENT_TO_RECIPE = "addIngredientToRecipe";
	private static final String ADD_SPECIALTY = "addSpecialty";
	private static final String LINK_RECIPE_TO_SPECIALTY = "linkRecipeToSpecialty";
	private static final String ADD_RESTAURANT = "addRestaurant";
	private static final String LINK_RECIPE_TO_RESTAURANT = "linkRecipeToRestaurant";
	private static final String LIST_RECIPES_OF_RESTAURANT = "listRecipesOfRestaurant";
	private static final String ADD_CUISINE = "addCuisine";
	private static final String LINK_RECIPE_TO_CUISINE = "linkRecipeToCuisine";
	private static final String LIST_RECIPES_BY_CUISINE = "listRecipesByCuisine";
	private static final String ADD_GOURMET = "addGourmet";
	private static final String LINK_GOURMET_TO_RESTAURANT = "linkGourmetToRestaurant";
	private static final String RATE = "rate";
	private static final String LIST_BEST_RECIPES = "listBestRecipes";

	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Constructor.
	 */
	public Main() {
		// not necessary if extending Object.
		super();
		this.setOut((PrintWriter) null);
		this.setIn((BufferedReader) null);
	}

	/**
	 * @param in
	 *            the BufferedReader to set
	 */
	private void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * @param in
	 *            the BufferedReader to set
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private void setIn(String in) throws UnsupportedEncodingException, FileNotFoundException {
		this.setIn(this.buildReader(in));
	}

	/**
	 * @param out
	 *            the PrintWriter to set
	 */
	private void setOut(PrintWriter out) {
		this.out = out;
	}

	/**
	 * @param out
	 *            the PrintWriter to set
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private void setOut(String out) throws UnsupportedEncodingException, FileNotFoundException {
		this.setOut(this.buildWriter(out));
	}

	/**
	 * Builds the output stream.
	 * 
	 * @param fileName
	 *            the output filename or 'SCREEN'
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private PrintWriter buildWriter(String fileName) throws UnsupportedEncodingException, FileNotFoundException {
		PrintWriter out = null;
		if (fileName.equals(SCREEN)) {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8")), true);
		} else {
			File f;
			f = new File(fileName);
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f.getAbsolutePath(), false), "UTF-8")));
		}
		return out;
	}

	/**
	 * Builds the input stream.
	 * 
	 * @param fileName
	 *            the input stream filename or "KEYBOARD"
	 * @return BufferedReader the in stream
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private BufferedReader buildReader(String fileName) throws UnsupportedEncodingException, FileNotFoundException {
		BufferedReader in = null;
		if (fileName.equals(KEYBOARD)) {
			in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		} else {
			File f = new File(fileName);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		}
		return in;
	}

	/**
	 * Finalize. Let's ensure the streams have been closed
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// finalization: ensure the streams are closed
		if (this.getOut() != null) {
			this.getOut().close(); // not exception
		}
		if (this.getIn() != null) {
			this.getIn().close(); // ioexception
		}
		// not necessary if extending Object.
		super.finalize();
	}

	/**
	 * Input stream getter.
	 * 
	 * @return the input stream
	 */
	private BufferedReader getIn() {
		return (this.in);
	}

	/**
	 * Output stream getter.
	 * 
	 * @return the output stream
	 */
	private PrintWriter getOut() {
		return (this.out);
	}

	/**
	 * Program entry point.
	 * 
	 * @param args
	 *            arguments to the program
	 */
	public static void main(String[] args) {
		Main p = null;
		HackingCook th = new HackingCook();

		if (args.length == 2) {
			p = new Main();
			try {
				p.setOut(args[1]);
				p.setIn(args[0]);
				p.treatInput(th);
			} catch (Exception e) {
				e.printStackTrace(System.err);
				// System.err.println(e.getMessage());
			} finally {
				if (p.getOut() != null) {
					p.getOut().close(); // not exception
				}
				if (p.getIn() != null) {
					try {
						p.getIn().close(); // ioexception
					} catch (IOException e) {
						e.printStackTrace(System.err);
						// System.err.println(e.getMessage());
					}
				}
			}
		} else {
			System.err.println("Number of parameters incorrect, syntax is:" + NL + "\t>java Main in out");
			System.err.println("where \tin  is " + KEYBOARD + " or InputFile");
			System.err.println("and \tout is " + SCREEN + "   or OutputFile");
			System.exit(-1);
		}
	}

	/**
	 * Treats the input and outputs the results.
	 * 
	 * @param theatremanager
	 *            the Theatre Manager instance on which to delegate
	 * @throws Exception
	 *             In case of non I/O error
	 */
	public void treatInput(HackingCook educationalCenterDeliveryUnit) throws Exception {
		String currentLine;
		boolean end = false;
		while (!end) {
			try {
				currentLine = in.readLine();
				if ((currentLine != null)) {
					processCommand(educationalCenterDeliveryUnit, currentLine);
				} else {
					end = true;
				}
			} catch (IOException ex) {
				ex.printStackTrace(System.err);
			}
		}
	}

	/**
	 * Processes the line command, delegating on 'tm'.
	 * 
	 * @param tm
	 *            Theatre Manager The instance on which to delegate
	 * @param currentLine
	 *            the current line to process
	 * @throws Exception
	 */
	/**
	 * @param du
	 * @param currentLine
	 * @throws Exception
	 */
	private void processCommand(HackingCook hc, String currentLine) throws Exception {
		String line = currentLine.trim();
		if (line.length() == 0) {
			line = line + ",PHANTOM";
		} else if ("(,)".indexOf(line.charAt(line.length() - 1)) == -1) {
			line = line + ",PHANTOM";
		} else {
			line = line + "PHANTOM";
		}
		String[] st = line.split("\\(|,|\\)", -1);
		st = Arrays.copyOf(st, st.length - 1);

		// Special case to control 0 argument list
		if (st.length == 2 && st[1].isEmpty()) {
			String[] st2 = { st[0] };
			st = st2.clone();
		}

		for (int i = 0; i < st.length; i++) {
			st[i] = st[i].trim();
		}
		if (st[0].startsWith(COMMENT_LINE) || st[0].trim().equals("")) {
			this.getOut().println(currentLine);
		} else {
			this.getOut().println(currentLine);
			try {
				if (st[0].equalsIgnoreCase(Main.ADD_INGREDIENT)) {
					if (st.length == 6 || st.length == 7) {
						String code = st[1];
						String name = st[2];
						boolean isPrepared = Boolean.parseBoolean(st[3]);
						float quantity = Float.parseFloat(st[4]);
						String unity = st[5];
						if (!isPrepared) {
							hc.addIngredient(code, name, quantity, unity);
						} else {
							int cookingTime = Integer.parseInt(st[6]);
							hc.addIngredient(code, name, quantity, unity, cookingTime);
						}
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}
					// addRecipe(code, name, preparationMode)
				} else if (st[0].equalsIgnoreCase(Main.ADD_RECIPE)) {
					if (st.length == 4) {
						int code = Integer.parseInt(st[1]);
						String name = st[2];
						String preparationMode = st[3];
						hc.addRecipe(code, name, preparationMode);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}
				} else if (st[0].equalsIgnoreCase(Main.ADD_INGREDIENT_TO_RECIPE)) {
					if (st.length == 3) {
						String ingredientCode = st[1];
						int recipeCode = Integer.parseInt(st[2]);
						hc.addIngredientToRecipe(ingredientCode, recipeCode);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				} else if (st[0].equalsIgnoreCase(Main.ADD_SPECIALTY)) {
					if (st.length == 3) {
						int code = Integer.parseInt(st[1]);
						String name = st[2];
						hc.addSpecialty(code, name);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}
				} else if (st[0].equalsIgnoreCase(Main.LINK_RECIPE_TO_SPECIALTY)) {
					if (st.length == 3) {
						int recipeId = Integer.parseInt(st[1]);
						int specialtyId = Integer.parseInt(st[2]);
						hc.linkRecipeToSpecialty(recipeId, specialtyId);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}
					// #addRestaurant(code, name, postalCode)
				} else if (st[0].equalsIgnoreCase(Main.ADD_RESTAURANT)) {
					if (st.length == 4) {
						int code = Integer.parseInt(st[1]);
						String name = (st[2]);
						String postalCode = (st[3]);
						hc.addRestaurant(code, name, postalCode);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}
					// #linkRecipeToRestaurant(recipeId, restaurantId)

				} else if (st[0].equalsIgnoreCase(Main.LINK_RECIPE_TO_RESTAURANT)) {
					if (st.length == 3) {
						int recipeId = Integer.parseInt(st[1]);
						int restaurantId = Integer.parseInt(st[2]);
						hc.linkRecipeToRestaurant(recipeId, restaurantId);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

					// #listRecipes(restaurantId)

				} else if (st[0].equalsIgnoreCase(Main.LIST_RECIPES_OF_RESTAURANT)) {
					if (st.length == 2) {
						int restaurantId = Integer.parseInt(st[1]);
						String list = hc.listRecipesOfRestaurant(restaurantId);
						this.getOut().println(list);
						this.getOut().println("-ack");

					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				}
				// #addCuisine(code, name, belongCuisineCode)
				else if (st[0].equalsIgnoreCase(Main.ADD_CUISINE)) {
					if (st.length == 4) {
						String code = (st[1]);
						String name = st[2];
						String codeBelongTo = st[3];

						hc.addCuisine(code, name, codeBelongTo);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				} else if (st[0].equalsIgnoreCase(Main.LINK_RECIPE_TO_CUISINE)) {
					if (st.length == 3) {
						int recipeId = Integer.parseInt(st[1]);
						String cuisineId = (st[2]);
						hc.linkRecipeToCuisine(recipeId, cuisineId);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

					// listRecipesByCuisine(restaurantId, cuisineId)
				} else if (st[0].equalsIgnoreCase(Main.LIST_RECIPES_BY_CUISINE)) {
					if (st.length == 3) {
						int restaurantId = Integer.parseInt(st[1]);
						String cuisineId = st[2];
						String list = hc.listRecipesByCuisine(restaurantId, cuisineId);
						this.getOut().println(list);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				} else if (st[0].equalsIgnoreCase(Main.ADD_GOURMET)) {
					if (st.length == 2) {
						String code = (st[1]);
						hc.addGorumet(code);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				}

				// #linkGourmetToRestaurant(gourmetId, restaurantId)
				else if (st[0].equalsIgnoreCase(Main.LINK_GOURMET_TO_RESTAURANT)) {
					if (st.length == 3) {
						String gourmetId = (st[1]);
						int restaurantId = Integer.parseInt(st[2]);
						hc.linkGourmetToRestaurant(gourmetId, restaurantId);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				}

				// #rate(gourmetId, recipeId, rate)
				else if (st[0].equalsIgnoreCase(Main.RATE)) {
					if (st.length == 4) {
						String gourmetId = (st[1]);
						int recipeId = Integer.parseInt(st[2]);
						float rate = Float.parseFloat(st[3]);
						hc.rate(gourmetId, recipeId, rate);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				} else if (st[0].equalsIgnoreCase(Main.LIST_BEST_RECIPES)) {
					if (st.length == 1) {
						String list = hc.listBestRecipes();
						this.getOut().println(list);
						this.getOut().println("-ack");
					} else {
						this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
					}

				}

				else {
					this.getOut().println(WRONG_COMMAND);
				}
			} catch (HackingCookException hce) {
				this.getOut().println(hce.getMessage());
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	} // processCommand
} // Main