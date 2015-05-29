import java.net.*; // se importa el paquete de red de la API
import java.io.*; // se importa el paquete de I/O de la API

/*
Basicamente, el funcionamiento de esta clase consiste en recibir datos por un puerto asociado a un stream
de salida e imprimirlos por pantalla. La ejecucion de este programa termina cuando la cadena de caracteres que se 
recibe en el datagrama empieza por "end".
*/

public class servidorudp { // definicion de la clase

	public static void main(String argv[]) { // metodo main para que la clase se pueda ejecutar
		DatagramSocket socket; // se crea una referencia a un Socket UDP (implicitamente apunta a null)
		boolean fin = false; // se crea una variable booleana inicializada en false

		try { // se controla el lanzamiento de excepciones
			socket = new DatagramSocket(6000); // se asocia a la referencia socket un Socket UDP en el puerto 6000
			do { // haz (se ejecuta este bucle)
				byte[] mensaje_bytes = new byte[256]; // se crea un array de 256 bytes (buffer)
				DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 256); // se crea un datagrama asociado al array de bytes (buffer) y con el tamaño del mismo
				socket.receive(paquete); // se introduce el contenido (datagrama) que se reciba por el puerto en el datagrama anteriormente creado
				String mensaje = ""; // se crea un string "mensaje" vacio
				mensaje = new String(mensaje_bytes); // se asocia al mensaje anterior un string creado desde el contenido del buffer
				System.out.println(mensaje); // se imprime el mensaje
				if (mensaje.startsWith("end")) // si el mensaje comienza con la cadena de caracteres "end"
					fin = true; // se marca la variable booleana fin como true
			} while (!fin); // mientras fin sea false
		}
		catch (Exception e) { // en caso de que se lance alguna excepcion se trata aqui
			System.err.println(e.getMessage()); // se imprime el mensaje de la excepcion
			System.exit(1); // se sale de la ejecucion
		}
	}
}
