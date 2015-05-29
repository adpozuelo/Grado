/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise3.Step6;

import java.util.Date;

/**
 *
 * @author adpozuelo
 */
public class Record {
    
    private Date date;  // Atributo fecha.
    private double percentage;  // Atributo porcentaje
    private DisabledCustomer disabledCustomer;  // Atributo representa la asociatividad con la clase DisableCustomer.
    private Disabled disabled;  // Atributo representa la asociatividad con la clase Disabled.
    
    // Constructor predeterminado.
    public Record() {}
    
    // Constructor. Utilizo "Setters".
    public Record(Date dateValue, double percentageValue) {
        setDate(dateValue);
        setPercentage(percentageValue);
    }

    // Metodos "Getters" y "Setters"
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public DisabledCustomer getDisabledCustomer() {
        return disabledCustomer;
    }

    public void setDisabledCustomer(DisabledCustomer disabledCustomer) {
        this.disabledCustomer = disabledCustomer;
    }

    public Disabled getDisabled() {
        return disabled;
    }

    public void setDisabled(Disabled disabled) {
        this.disabled = disabled;
    }
    
    
}
