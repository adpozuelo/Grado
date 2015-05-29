import java.net.*; // se importa el paquete de red de la API
import java.io.*; // se importa el paquete de I/O de la API

/*
Basicamente, el funcionamiento de esta clase es leer datos por la consola y mandarselos al servidor dentro
de un stream asociado al puerto. La ejecucion de este programa termina cuando la cadena de caracteres que
introducimos por consola empieza por "end".
*/

public class clientetcp { // definicion de la clase

	public static void main(String argv[]) { // metodo main para que la clase se pueda ejecutar
		if (argv.length == 0) { // si el numero de argumentos de la ejecicion es igual a cero
			System.err.println("java clientetcp servidor"); // se imprime error con ejemplo de invocacion
			System.exit(1); // se sale de la ejecucion
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // se crea un buffer de entrada asociado a un stream de entrada asociado a la entrada por consola
		Socket socket; // se crea una referencia a un Socket TCP (implicitamente apunta a null)
		InetAddress address; // se crea una referencia a un InetAddress (implicitamente apunta a null)
		byte[] mensaje_bytes = new byte[256]; // se crea un array de 256 bytes
		String mensaje=""; // se crea un string "mensaje" vacio

		try { // se controla el lanzamiento de excepciones

			address=InetAddress.getByName(argv[0]); // se asocia a la referencia address la direccion (cadena de caracteres) del "servidor" introducido como parametro de invocacion de este programa. El metodo getByName transforma la cadena de caracteres en un objeto InetAddress valido
			socket = new Socket(address, 6001); // se asocia al socket un socket TCP conectado con la direccion anterior y en el puerto 6001
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // se crea y se asocia un stream de salida con el stream de salida del socket creado y conectado anteriormente
			do { // haz (se ejecuta este bucle)
				mensaje = in.readLine(); // se leen los datos (teclas) introducidas por consola y se introducen en el string mensaje
				out.writeUTF(mensaje); // se escriben (en UTF) en el stream de salida (asociado al socket)
			} while (!mensaje.startsWith("end")); // mientras el mensaje no comience con el string "end"
		}
		catch (Exception e) { // en caso de que se lance alguna excepcion se trata aqui
			System.err.println(e.getMessage()); // se imprime el mensaje de la excepcion
			System.exit(1); // se sale de la ejecucion
		}
	}
}
