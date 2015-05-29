/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercise1.Step2;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author adpozuelo
 */
public class RaceBike extends Bike {
    
    protected String complement[];  // Atributo array de complementos.
    
    // Constructor.
    public RaceBike(double price, Integer size, Date manufactureDate, String complements[]) {
        super(price, size,  manufactureDate);  // Llamo al constructor de la superclase.
        this.complement = complements;  // Asigno el parametro de entrada a la referencia del atributo complement.
        System.out.println("Constructor --> RaceBike created");  // Salida segun enunciado.
    }
    
    // Metodo sobreescrito toString.
    public String toString() {
        String complements = "";  // Creo un String para almacenar los complementos.
        for (int i = 0; i < this.complement.length; i++) {  // Recorro el array de complementos.
            // Añado al String los complementos. Si es el ultimo complemento añado no añado coma.
            complements += this.complement[i].toString() + ((i < (this.complement.length - 1) ? ", " : ""));
        }
        return super.toString() + Bike.LF + "  Complements: " + complements;  // Devuelvo la salida segun enunciado.
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
                parcial = parcial * 1.4;  // Se aplica el descuento o incremento expuesto en el enunciado
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
