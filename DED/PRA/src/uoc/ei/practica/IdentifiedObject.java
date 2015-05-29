package uoc.ei.practica;

/** 
 * Representa un objeto identificado mediante un String.
 */
public class IdentifiedObject {

	
	protected static final String LS = System.getProperty("line.separator");
	protected static final String PREFIX = "\t";

	protected String identifier;
	
	public IdentifiedObject() {
		
	}
	
	public IdentifiedObject(String identifier) {
		this.identifier=identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier=identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String toString() {
	    StringBuffer result = new StringBuffer();
	    result.append("id: "+this.getIdentifier() + Messages.LS);
		return  result.toString();
	}


}
