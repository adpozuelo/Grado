package uoc.ei.practica;

/**
 * Clase principal encargada de ejecutar un juego de pruebas que proviene de un 
 * fichero de entrada. Los resultados se escriben en un fichero de salida.
 *
 * @author   Equipo docente de Estructura de la Información de la UOC
 * @version  Otoño 2010
 */
public class TestPractica  extends Test
{
   /** Constructor con dos parámetros. */
   public TestPractica(String gestor, String[] args)
   {
      super(gestor, args);
   }

   /**
    * Método de inicio de ejecución (programa principal).
    * 1.- Se debe instanciar una prueba asociada a un gestor.
    * 2.- Ejecución de prueba.
    */
   public static void main(String[] args)
   {
      Test p;
      {
          p = new TestPractica("uoc.ei.practica.BicingManagerImpl", args);
         p.execute();
      }
   }
}
