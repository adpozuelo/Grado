/**
 * ISCSD - PRA2
 * Antonio Díaz Pozuelo
 * adpozuelo@uoc.edu
 */
import java.sql.*;
/**
 * Implementation Class, that follows the DAO pattern, the operations of actions and consults 
 * the table Category
 **/
public class RMIEAgendaImplDAO implements RMIEAgendaInterfaceDAO{
	/**
	 * Constructors
	 */
	public RMIEAgendaImplDAO(){}
	/**
	 * Method that get all the details of a category from database and return requested category
	 * @return 
	 */
	public RMICategoryTO showCat(int catId)
	{
		PreparedStatement pstmt = null; // Prepared statement to database
		Connection conn = null; // Connection to database
		RMICategoryTO catVO = new RMICategoryTO(); // Category to return

		try {
			System.out.println("findCat called"); // Log event
			conn = getConnection(); //Get DB connection
			pstmt = conn.prepareStatement("SELECT * FROM postgres.pra2.category WHERE id = ?"); // Prepare the database statement
			pstmt.setInt(1, catId); // Change ? in the database statement with requested category's id
			ResultSet rs = pstmt.executeQuery(); // Execute the statement and get the result
			while (rs.next()) { // Iterate over the result
				// Set database category's values into category's atributes that will be return
				Integer id  = new Integer(rs.getInt("id"));
				catVO.setId(id.toString());
				catVO.setName(rs.getString("name"));
				catVO.setDescription(rs.getString("description"));
			}
			return catVO; // return category
		}
		catch (SQLException sqle)
		{
			System.out.println("Error!. Can not access the database Category: "+sqle); 
		}
		catch (Exception e)
		{
			System.out.println("Error!. Can not access the database Category: "+e); 
		}
		finally {
			/*
			 * Release DB Connection for other beans
			 */
			try { if (pstmt != null) pstmt.close(); }
			catch (Exception e) {}
			try { if (conn != null) conn.close(); }
			catch (Exception e) {}
		}
		return catVO;
	}
	
	/**
	 * Gets JDBC connection from the connection pool.
	 * @return The JDBC connection
	 */
	public Connection getConnection() throws Exception {
		try {			
	        Class.forName("org.postgresql.Driver");	
			java.sql.Connection conn;	
	        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","USER","PASSWORD");			
			return conn;
		}
		catch (Exception e) {
			System.err.println("Could not locate datasource! Reason:");
			e.printStackTrace();
			throw e;
		}
	}
}