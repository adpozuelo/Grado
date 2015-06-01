package e3;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.html.HTMLDocument;

/**
 * Sample class of naive bayes classifier. Class to represent a sample of
 * dataset. UOC AC PRA - Antonio Díaz Pozuelo
 *
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
public class SampleE3 {

    private String kind; // Sample class.
    private ArrayList<String> attributes; // Sample attributes.

    /**
     * Constructor.
     *
     * @param kind
     * @param attributes
     */
    public SampleE3(String kind, ArrayList<String> attributes) {
        this.kind = kind;
        this.attributes = attributes;
    }

    /**
     * Getters and Setters.
     */
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * toString method.
     *
     * @return Sample's string representation.
     */
    @Override
    public String toString() {
        String out = this.kind;
        Iterator<String> it = this.attributes.iterator();
        while (it.hasNext()) {
            out += " " + it.next();
        }
        return out;
    }
}
