import java.util.Comparator;

/**
 * RateComparator class of the HackingCook JAVA Practice.
 * </br>Rate comparator based in mean.
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */

public class RateComparator implements Comparator {

    /**
     * Compares its two arguments for order. 
     * Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     * @param t
     *           the first object to be compared.
     * @param t1
     *           the second object to be compared.
     * @return
     *          a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Object t, Object t1) {
        return Float.compare(((Recipe)t1).getMean(), ((Recipe)t).getMean());
    }
    
}
