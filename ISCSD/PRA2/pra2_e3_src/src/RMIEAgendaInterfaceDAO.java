/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
/**
 * Interface, that follows the DAO pattern, that defines all the operations of actions and consults the table Category
 **/
public interface RMIEAgendaInterfaceDAO {
	/**
	 * Method that get all the details of a category from database and return requested category
	 * @return 
	 */
	public RMICategoryTO showCat(int cat) throws Exception;
}
