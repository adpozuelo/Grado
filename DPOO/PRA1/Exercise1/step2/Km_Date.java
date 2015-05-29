/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise1.Step2;

import java.util.Date;

public class Km_Date {

    private Date date;
    private double km;
    
    
    /**
     * 
     * @param date
     * @param km 
     */
    public Km_Date(double km,Date date){
        this.km = km;
        this.date = date;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the km
     */
    public double getKm() {
        return km;
    }
    
    
}
