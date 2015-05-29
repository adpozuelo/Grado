package uoc.ei.practica;

import  java.io.*;
import  java.util.*;
import  java.lang.reflect.Method;
import  java.text.SimpleDateFormat;

/**
 * Clase encargada de leer un juego de pruebas escritas en un fichero de
 * texto, de ejecutarlas y de escribir los resultados en otro fichero también
 * <i>plain text</i>.
 * Los nombres por defecto de los ficheros <i>plain text</i> son <code>in.txt
 * </code> y <code>out.txt</code>.
 * Admite String's, tipos de datos primitivos y fechas (java.util.Date) en
 * formato "dd-MM-yyyy HH:mm:ss"
 *
 * @author   Equipo docente de Estructura de la Información de la UOC
 * @version  Otoño 2010
 */
public class Test
{
   /** Salto de lónea de la plataforma. */
   public static final String LS = System.getProperty("line.separator");

   /**
    * Separador del nombre del método y de sus parámetros, en el fichero
    * de entrada.
    */
   public static final String TOKEN = ",";

   /**
    * Símbolo que se pone al principio de una línea del fichero de entrada por
    * indicar que se trata de un comentario.
    */
   public static final String REMARK = "#";

   /** Formato que han de tener los parámetros de tipo java.util.Date. */
   public static final String DATE = "dd-MM-yyyy HH:mm:ss";

   /** Instancia de la clase gestora. */
   Object gestor;

   /** M�todos de la clase gestora. */
   Map<Operation, Method> operations;

   /** Nombre del fichero de entrada con el juego de pruebas. */
   private String inFile = "in.txt";

   /** Nombre del fichero de salida con los resultados de las pruebas. */
   private String outFile = "out.txt";

   /** Lector del fichero de entrada. */
   private BufferedReader in;

   /** Escritor del fichero de salida. */
   private PrintStream out;

   /**
    * Constructor con dos parámetros.
    * @param gestor  nombre completo de la clase gestora (package.name)
    * @param args  array con el nombre de los ficheros de entrada y de salida;
    *              si no hay dos argumentos, se usan los nombres por defecto:
    *              <code>in.txt</code> y <code>out.txt</code>
    */
   public Test(String gestor, String[] args)
   {
      try
      {
         Class<? extends Object> clazz = Class.forName(gestor);
         this.gestor = clazz.newInstance();
         loadMethods(clazz.getMethods());
      }
      catch (Exception ex)
      {
         System.err.println("ERROR: en instanciar la clase gestora (" +
                             gestor + ")");
         ex.printStackTrace();
         System.exit(-1);
      }

      if (args.length == 2) { inFile = args[0];  outFile = args[1]; }

      try
      {
         in = new BufferedReader(new FileReader(args[0]));
         out = new PrintStream(new FileOutputStream(args[1]));
      }
      catch (Exception ex)
      {
         System.err.println("ERROR: en los ficheros de entrada/salida (" +
                             inFile + " o " + outFile + ")");
         ex.printStackTrace();
         System.exit(-1);
      }
   }

   /**
    * Método que carga las operaciones de la clase gestora en una tabla.
    * No hay soporte para métodos homónimos con el mismo número de
    * parámetros.
    * @param methods  array con los métodos de la clase gestora
    */
   private void loadMethods(Method[] methods)
   {
      operations = new Hashtable<Operation,Method>();
      for(Method m: methods)
      {
         Operation op = new Operation(m);
         if (operations.containsKey(op))
         {
            System.err.println("ERROR: No hay soporte para metodos " +
               "homonimos con el mismo numero de parametros:" + LS + m);
            System.exit(-1);
         }
         operations.put(op, m);
      }
   }

   /**
    * Método que lee el fichero de entrada y ejecuta las operaciones
    * indicadas de la clase gestora, con sus parámetros.
    */
   public void execute()
   {
      try
      {
         String line;
         while ((line = in.readLine()) != null)
         {
            line = line.trim();
            if ( line.length() == 0 || line.startsWith(REMARK) )
               out.println(line);
            else
               executeOperation(line);
         }
      }
      catch (IOException ioex)
      {
         System.err.println("ERROR: en leer el fichero de entrada (" +
                             inFile + ")");
         ioex.printStackTrace();
         System.exit(-1);
      }
   }

   /**
    * Método que invoca una operación con sus argumentos.
    * @param line  nombre del método y valor de sus parámetros, en formato
    *        String, separados por un delimitador (Test.TOKEN)
    * @return  resultado que retorna el método
    */
   private Object executeOperation(String line)
   {
      Object result = null;
      String[] tokens = line.split(TOKEN);
      Operation op = new Operation(tokens);
      Method method = operations.get(op);
      if (method == null)
      {
         out.println("ERROR: El metodo " + op + " no existe");
      }
      else
      {
         Object[] args = getParams(method, tokens);
         try
         {
            out.println(op);
            result = method.invoke(gestor, args);
            if (result == null)  out.println("void");
            else  printResult(result);
         }
         catch (Exception ex)
         {
            if (ex.getCause() instanceof EIException)
            {
               out.println("ERROR: " + ex.getCause().getMessage());
            }
            else
            {
               System.err.println("ERROR: en invocar el metodo " + op);
               ex.printStackTrace();
               System.exit(-1);
            }
         }
      }
      return  result;
   }

   /**
    * Método que retorna, mediante un array de objetos, los parámetros
    * de la operación considerada.
    * @param method  método con la definición de sus parámetros
    * @param tokens  nombre del método y valor de sus parámetros en
    *        formato String
    * @return  un array con el valor de los parámetros convertido al tipo de
    *          datos correspondiente
    */
   private Object[] getParams(Method method, String[] tokens)
   {
      Class<? extends Object>[] paramTypes = method.getParameterTypes();
      Object[] result = new Object[paramTypes.length];
      for (int i = 0; i < paramTypes.length; i++)
      {
         String paramType = paramTypes[i].getName();
         result[i] = wrapParam(tokens[i+1].trim(), paramType);
      }
      return  result;
   }

   /**
    * Método que retorna el valor de un parámetro moldeado (wrapped)
    * de acuerdo con el tipo de datos considerado.
    * @param param  valor del parámetro en formato String
    * @param type  nombre del tipo de datos que corresponde al parámetro
    * @return  objeto con valor del parámetro moldeado al tipo de datos
    */
   private Object wrapParam(String param, String type)
   {
      Object result = param;
      try
      {
         if (type.equals("boolean")     ||
            type.equals("java.lang.Boolean"))
            result = new Boolean(param);
         else if (type.equals("byte")   ||
            type.equals("java.lang.Byte"))
            result = new Byte(param);
         else if (type.equals("short")  ||
            type.equals("java.lang.Short"))
            result = new Short(param);
         else if (type.equals("int")    ||
            type.equals("java.lang.Integer"))
            result = new Integer(param);
         else if (type.equals("long")   ||
            type.equals("java.lang.Long"))
            result = new Long(param);
         else if (type.equals("float")  ||
            type.equals("java.lang.Float"))
            result = new Float(param);
         else if (type.equals("double") ||
            type.equals("java.lang.Double"))
            result = new Double(param);
         else if (type.equals("char")   ||
            type.equals("java.lang.Character"))
            result = new Character(param.charAt(0));
         else if (type.equals("java.util.Date"))
         {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE);
            result = sdf.parse(param);
         }
      }
      catch (NumberFormatException nfex)
      {
         out.println("ERROR: Se esperaba un " + type + " y hay "+ param);
      }
      catch (java.text.ParseException pex)
      {
         out.println("ERROR: Se esperaba un formato " + DATE + " y hay " +
                     param);
      }
      return  result;
   }

   /**
    * Método que imprime un resultado en el fichero de salida.
    * @param result  resultado no nulo y no vacío que se quiere imprimir
    */
private void printResult(Object result)
   {
      if (result instanceof uoc.ei.tads.Iterador<?>)
      {
         for (uoc.ei.tads.Iterador<?> it = (uoc.ei.tads.Iterador<?>)result;
                                   it.haySiguiente(); )
         {
            out.println(it.siguiente());
         }
      }
      else  out.println(result.toString());
   }
}

/**
 * Clase auxiliar para poder distinguir métodos homónimos cuando tienen
 * diferente número de argumentos.
 *
 * @author   Equipo docente de Estructura de la Información de la UOC
 * @version  Primavera 2007
 */
class Operation
{
   /** Nombre del método. */
   private final String name;

   /** Número de parámetros del método. */
   private final int count;

   /** Signatura del método. */
   private String head = "";

   /**
    * Constructor con un parámetro.
    * @param method  información sobre el método considerado
    */
   public Operation(Method method)
   {
      name  = method.getName();
      count = method.getParameterTypes().length;
   }

   /**
    * Constructor con un parámetro.
    * @param tokens  nombre del método y valor de sus parámetros en
    *        formato String
    */
   public Operation(String[] tokens)
   {
      name  = tokens[0];
      count = tokens.length - 1;
      String args = "";
      for (int i = 1; i < tokens.length; i++)
      {
         args += (", " + tokens[i].trim());
      }
      if (args.length() > 1)  args = args.substring(2); // suprime 1a coma

      head = name + '(' + args + ')';
   }

   /**
    * Sobrescribe el método equals definido a Object.
    * @param obj  objeto de la misma clase o descendientes
    * @return  cierto, si el objeto recibido es no nulo, de la misma clase y
    *          tiene el mismo número de parámetros; falso, en otro caso
    */
   public boolean equals(Object obj)
   {
      boolean result = false;
      if (obj != null && obj instanceof Operation)
      {
         Operation other = (Operation)obj;
         result = name.equals(other.name) && (count == other.count);
      }
      return  result;
   }

   /**
    * Sobrescribe el método hashCode definido a Object.
    * @return  un código de dispersión para el objeto
    */
   public int hashCode() { return  name.hashCode(); }

   /**
    * Método que redefine la conversión del objeto a String.
    * @return  cadena de caracteres con el nombre de la operación y el
    *          número de parámetros, o su valor si es conocido.
    */
   public String toString()
   {
      return  (head.length() == 0) ?
              (name + " con " + count + " argumentos") : head;
   }
}
