import java.net.*; // se importa el paquete de red de la API
import java.io.*; // se importa el paquete de I/O de la API

/*
Basicamente, el funcionamiento de esta clase es leer datos por la consola y mandarselos al servidor dentro
de un stream asociado al puerto. La ejecucion de este programa termina cuando la cadena de caracteres que
introducimos por consola empieza por "end".
*/

public class clienteudp { // definicion de la clase

	public static void main(String argv[]) { // metodo main para que la clase se pueda ejecutar
		if (argv.length == 0) { // si el numero de argumentos de la ejecicion es igual a cero
			System.err.println("java clienteudp servidor"); // se imprime error con ejemplo de invocacion
			System.exit(1); // se sale de la ejecucion
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // se crea un buffer de entrada asociado a un stream de entrada asociado a la entrada por consola
		DatagramSocket socket; // se crea una referencia a un Socket UDP (implicitamente apunta a null)
		InetAddress address; // se crea una referencia a un InetAddress (implicitamente apunta a null)
		byte[] mensaje_bytes = new byte[256]; // se crea un array de 256 bytes
		String mensaje = ""; // se crea un string "mensaje" vacio
		DatagramPacket paquete; // se crea una referencia a un DatagramPacket que son los paquetes a enviar

		mensaje_bytes = mensaje.getBytes(); // se codifica el string mensaje en una secuencia de bytes y se asocian al array de bytes creado anteriormente (es el buffer)
		try { // se controla el lanzamiento de excepciones
			socket = new DatagramSocket(); // se asocia a la referencia socket un Socket UDP (instanciacion)
			address = InetAddress.getByName(argv[0]); // se asocia a la referencia address la direccion (cadena de caracteres) del "servidor" introducido como parametro de invocacion de este programa. El metodo getByName transforma la cadena de caracteres en un objeto InetAddress valido
			do { // haz (se ejecuta este bucle)
				mensaje = in.readLine(); // se leen los datos (teclas) introducidas por consola y se introducen en el string mensaje
				mensaje_bytes = mensaje.getBytes(); // se codifica el string mensaje en una secuencia de bytes y se asocian al array de bytes creado anteriormente (es el buffer)
				paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000); // se asocia la referencia paquete a un objeto DatagramPacket valido (creado mediante la secuencia de bytes del buffer, el tamaño del mensaje y la direccion y puerto del servidor [6000])
				socket.send(paquete); // se envia el datagrama a traves del stream asociado al puerto
			} while (!mensaje.startsWith("end")); // mientras el mensaje no comience con el string "end"
		}
		catch (Exception e) { // en caso de que se lance alguna excepcion se trata aqui
			System.err.println(e.getMessage()); // se imprime el mensaje de la excepcion
			System.exit(1); // se sale de la ejecucion
		}
	}
}
