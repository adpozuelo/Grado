import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * CHARGENClient: cliente CHARGEN para "Redes y Aplicaciones Internet" PRA2
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */

public class ChargenClient {
	
	// constantes como los puertos y el tamaño del buffer
	private static final int DEFAULT_CHARGEN_PORT = 19;
	private static final int BUFFER_SIZE = 512;

	/**
	 * Metodo main para ejecutar la clase desde la JVM
	 * @param args
	 */
	public static void main(String[] args) {

		// carga de la direccion del servidor desde consola
		System.out.println("Por favor, introduce la dirección del servidor Chargen");
		Scanner sc = new Scanner(System.in);
		String servidorString = sc.nextLine();
		sc.close();

		DatagramSocket socket; // se crea una referencia a un Socket UDP
		InetAddress address; // se crea una referencia a un InetAddress
		byte[] mensaje_bytes = new byte[BUFFER_SIZE]; // se crea e instancia el buffer (512 bytes)
		String mensaje = "inicio"; // se crea una cadena para iniciar la comunicacion
		DatagramPacket paqueteEnvio; // se crea una referencia a un datagrama
		mensaje_bytes = mensaje.getBytes(); // se añade al buffer el contenido de la cadena de iniciacion de la comunicacion
		
		try {
			socket = new DatagramSocket(); // se instancia el socket UDP
			// se convierte la direccion del servidor obtenida por consola en un objeto InetAdress
			address = InetAddress.getByName(servidorString);
			// se crea el datagrama a enviar con el buffer, el tamaño del mismo mensaje, la direccion del servidor y el puerto
			paqueteEnvio = new DatagramPacket(mensaje_bytes, mensaje.length(), address, DEFAULT_CHARGEN_PORT);
			socket.send(paqueteEnvio); // se envia el datagrama
			mensaje_bytes = new byte[BUFFER_SIZE]; // se "limpia" el buffer
			// se crea el datagrama a recibir con el buffer y el tamaño del mismo
			DatagramPacket paqueteRecepcion = new DatagramPacket(mensaje_bytes, BUFFER_SIZE);
			socket.receive(paqueteRecepcion); // se recibe el datagrama
			mensaje = new String(mensaje_bytes); // se convierte el buffer a un string
			System.out.println('\n' + mensaje); // se imprime por pantalla
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
