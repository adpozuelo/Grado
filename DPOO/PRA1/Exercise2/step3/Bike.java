package Exercise2.Step3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class represents a bike
 */
abstract class Bike {

    public static String LF =
            System.getProperty("line.separator");	// Line feed
    protected static SimpleDateFormat MY =
            new SimpleDateFormat("dd/MMM/yyyy", new Locale("es", "ES"));		// Month-year date format 
    private Date manufactureDate;   // Date of building of the bike 
    private Integer size;        // Size of the bike
    private List Km_Dates;	// Km
    protected double price;	// Price in euros

    /**
     * 
     * @param price
     * @param size
     * @param manufactureDate
     * @param km 
     */
    public Bike(double price, Integer size, Date manufactureDate) {
        this.size = size;
        this.manufactureDate = manufactureDate;
        this.price = price;
        this.Km_Dates = new ArrayList();
    }

    /**
     * @param km 
     * @param date    
     */
    public void add_Km_Dates(double km, Date date) {
        this.getKm_Dates().add(new Km_Date(km, date));
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Constructor. This is the constructor of the generic severance
     */
    public Bike() {
        System.out.println("Constructor --> Bike created");
    }

    /**
     * toString method. Shows the value of the attributes
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("Bike ");
        sb.append("price:" + this.price).append(" ");
        sb.append("size:" + this.size).append(" ");
        sb.append("date: " + MY.format(this.manufactureDate));
        sb.append(LF);
        for (int i = 0; i < this.getKm_Dates().size(); i++) {
            Km_Date km = (Km_Date) this.getKm_Dates().get(i);
            sb.append("km:" + km.getKm() + "date:" + km.getDate()).append(" ");
        }
     
        return sb.toString();
    }

    /**
     * computeKm method. Computes and returns the km between two dates
     * @param from
     * @param to
     * @return 
     */
    public double computeKm(Date from, Date to) {
        double result = 0;

        for (int i = 0; i < this.getKm_Dates().size(); i++) {
            Km_Date km_date = (Km_Date) this.getKm_Dates().get(i);
            if (km_date.getDate().after(from) && km_date.getDate().before(to)) {
                result += km_date.getKm();
            }
        }
        return result;
    }

    /**
     * computeKm method. Computes and returns the km between two dates
     */
    abstract double computeDifficulty(Date from, Date to);

    /**
     * @return the Km_Dates
     */
    public List getKm_Dates() {
        return Km_Dates;
    }
}
