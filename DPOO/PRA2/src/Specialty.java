/**
 * Specialty class of the HackingCook JAVA Practice.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class Specialty {
    
    /**
     * specialty's code.
     */
    private int code;
    
    /**
     * specialty's name.
     */
    private String name;

    /**
     * Constructor.
     * @param code
     *              specialty's code.
     * @param name
     *              specialty's name.
     */
    public Specialty(int code, String name) {
        this.code = code;
        this.name = name;
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
     * Indicates whether some other object is "equal to" this one.
     * @param o
     *          the reference object with which to compare.
     * @return
     *          true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return (new Integer(this.code)).equals(((Specialty)o).getCode());
    }

}
