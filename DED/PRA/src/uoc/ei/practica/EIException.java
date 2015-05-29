package uoc.ei.practica;

/**
 * Excepción específica del gestor.
 *
 * @author   Equipo docente de Estructura de la Información de la UOC
 * @version  Primavera 2012
 */
public class EIException  extends Exception
{

	private static final long serialVersionUID = -2577150645305791318L;

	/**
    * Constructor con un paràmetro.
    * @param msg  mensaje que debe mostrar la excepción.
    */
   public EIException(String msg) { super(msg); }
}
