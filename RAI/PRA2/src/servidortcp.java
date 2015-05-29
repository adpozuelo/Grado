import java.net.*; // se importa el paquete de red de la API
import java.io.*; // se importa el paquete de I/O de la API

/*
Basicamente, el funcionamiento de esta clase consiste en recibir datos por un puerto asociado a un stream
de salida e imprimirlos por pantalla. La ejecucion de este programa termina cuando el cliente se desconecta
del puerto en cuestion.
*/

public class servidortcp { // definicion de la clase

	public static void main(String argv[]) { // metodo main para que la clase se pueda ejecutar
		ServerSocket socket; // se crea una referencia a un ServerSocket (implicitamente apunta a null)
		boolean fin = false; // se crea una variable booleana inicializada en false

		try { // se controla el lanzamiento de excepciones
			socket = new ServerSocket(6001); // se asocia el socket a un objeto ServerSocket TCP en el puerto 6001
			Socket socket_cli = socket.accept(); // se crea un socket para asociarlo a una peticion de un cliente y se pone el socket servidor en espera de conexiones
			DataInputStream in = new DataInputStream(socket_cli.getInputStream()); // se crea un stream de entrada y se asocia al stream de entrada del socket que esta esperando conexiones
			do { // haz (se ejecuta este bucle)
				String mensaje = ""; // se crea un string "mensaje" vacio
				mensaje = in.readUTF(); // se lee del stream de entrada en UTF y se introduce en el string mensaje
				System.out.println(mensaje); // se imprime el mensaje
			} while (1 > 0); // mientras 1 sea mayor que cero, esto es: siempre. Esto quiere decir que el puerto se cerrara cuando el cliente desconecte, esto es: cuando el string de entrada comience con la secuencia "end"
		}
		catch (Exception e) { // en caso de que se lance alguna excepcion se trata aqui
			System.err.println(e.getMessage()); // se imprime el mensaje de la excepcion
			System.exit(1); // se sale de la ejecucion
		}
	}
}
