/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise2.Step3;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author adpozuelo
 */
public class ElectricBike extends UrbanBike{
    
    protected Integer autonomy;  // Atributo Integer de autonomia.
    
    // Constructor.
    public ElectricBike(double price, Integer size, Date manufactureDate, Collection complements, Integer autonomyValue) {
        super(price, size,  manufactureDate, complements);  // Llamo al constructor de la superclase.
        this.setAutonomy(autonomyValue);  // Utilizo el "setter" para asignar el valor de la autonomia.  
        System.out.println("Constructor --> ElectricBike created");  // Salida segun enunciado.
    }
    
    // Metodo "setter" para el atributo autonomy.
    public void setAutonomy(Integer autonomyValue) {
        this.autonomy = autonomyValue;
    }
    
    // Metodo sobreescrito toString.
    public String toString() {
        return super.toString() + Bike.LF + "  Autonomy: " + this.autonomy;
    }
    
    // Metodo sobreescrito computeDifficulty.
    protected double computeDifficulty(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();  // Calendar inicial del calculo.
        fromCal.setTime(from);  // Asigno el Date from al Calendar inicial del calculo.
        Calendar toCal = Calendar.getInstance();  // Calendar final del calculo.
        toCal.setTime(to);  // Asigno el Date to al Calendar final del calculo.
        Object toCalLessOneDay = toCal.clone();  // Clono el Calendar final para controlar el desfase de milisegundos
        // generado para incluir los km realizados a las 00:00:00:00 de cada dia. Si no hago esto, y dado que
        // los metodos after y before son excluyentes con los extremos, dichos eventos no se registran.
        ((Calendar)toCalLessOneDay).add(Calendar.DAY_OF_MONTH, -1);  // Le resto un dia al Calendar final de control.
        Object fromCalMoreOneDay = fromCal.clone();  // Clono el Calendar incial para generarme un intervalo de un dia.
        ((Calendar)fromCalMoreOneDay).add(Calendar.DAY_OF_MONTH, 1);  // Le sumo un dia al Calendar inicial+1Dia.
        ((Calendar)fromCalMoreOneDay).add(Calendar.MILLISECOND, 1);  // Le sumo un milisegundo de desfase al Calendar inicial+1Dia
        // De este modo los eventos con hora 00:00:00:00 dentro del intervalo se consideran.
        Date fromTmp = fromCal.getTime();  // Creo un Date con la fecha inicial del intervalo de un dia.
        Date toTmp = ((Calendar)fromCalMoreOneDay).getTime();  // Creo un Date con la fecha inicial del intervalo de un dia mas el desfase.
        double total = 0;  // Variable donde almaceno el total de km.
        double parcial = 0;  // Variable donde almaceno el parcial de km.
        do {  // Mientras el Calendar inicial del calculo sea anterior al Calendar final del calculo
            parcial = super.computeKm(fromTmp, toTmp);  // Asigno a parcial los km hechos durante un dia + desfase
            // Si lo km se han hecho durante los meses 5, 6 y 7 (Junio, Julio y Agosto)
            if ((fromCal.get(Calendar.MONTH) >= 5) && (fromCal.get(Calendar.MONTH) <= 7))
                parcial = parcial * 0.7;  // Se aplica el descuento o incremento expuesto en el enunciado
            else  // En caso contrario, esto es, los km no se han hecho durante los meses 5, 6 y 7 (Junio, Julio y Agosto)
                parcial = parcial * 0.4;  // Se aplica el descuento o incremento expuesto en el enunciado
            total += parcial;  // En total almaceno el parcial ya tratado.
            ((Calendar)fromCalMoreOneDay).add(Calendar.DAY_OF_MONTH, 1);  // Al Calendar inicial del dia+1 le sumo un dia.
            fromCal.add(Calendar.DAY_OF_MONTH, 1);  // Al Calendar inicial del dia le sumo un dia.
            if (fromCal.equals(toCalLessOneDay))  // Si el Calendar inicial esta en el ultimo dia del intervalo de calculo.
                ((Calendar)fromCalMoreOneDay).add(Calendar.MILLISECOND, -1);  // Corrigo el desfase para no salirme del intervalo dado.
            fromTmp = fromCal.getTime();  // Actualizo el Date inicial del dia con el nuevo dia.
            toTmp = ((Calendar)fromCalMoreOneDay).getTime();  // Actualizo el Date final del dia con el nuevo dia.
        } while (fromCal.before(toCal));  // Fin del ciclo while.
        return total;  // Devuelvo el total de km ya tratados.
    }
}
