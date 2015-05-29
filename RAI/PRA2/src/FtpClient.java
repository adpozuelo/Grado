import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * FTPClient: cliente FTP para "Redes y Aplicaciones Internet" PRA2
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */

public class FtpClient {

	// constantes como los puertos, el usuario y la contraseña
	private static final int DEFAULT_FTP_CONTROL_PORT = 21; 
	private static final String DEFAULT_USERNAME = "anonymous";
	private static final String DEFAULT_PASSWORD = "adpozuelo@uoc.edu";
	
	/**
	 * Metodo main para ejecutar la clase desde la JVM
	 * @param args
	 */
	public static void main(String[] args) {
		
		// carga de la direccion del servidor desde consola
		System.out.println("Por favor, introduce la dirección del servidor FTP");
		Scanner sc = new Scanner(System.in);
		String servidorString = sc.nextLine();
		sc.close();
		
		// creacion de todas las referencias que utiliza el programa
		Socket socketCliente21 = null; // socket cliente en el puerto 21
		DataOutputStream out21 = null; // stream de salida en el puerto 21
		DataInputStream in21 = null; // stream de entrada en el puerto 21
		Socket socketClienteDataPort = null; // socket cliente en el puerto de datos
		DataInputStream inDataPort = null; // stream de entrada en el puerto de datos
		byte[] mensaje_bytes = new byte[2048]; // buffer donde se almacenan las entradas y salidas de los streams
		// string para almacenar la respuesta del servidor al modo de comunicacion (pasivo) y poder manipularlo para
		// obtener el puerto por el que se realizara la comunicacion de datos
		String puertoPasivoString = null; 
	
		try {
			// creacion del socket cliente con el servidor introducido desde consola por el puerto de control
			socketCliente21 = new Socket(InetAddress.getByName(servidorString.trim()), DEFAULT_FTP_CONTROL_PORT);
			// instanciacion y asignacion del stream de salida con el puerto de control
			out21 = new DataOutputStream(socketCliente21.getOutputStream());
			// instanciacion y asignacion del stream de entrada con el puerto de control
			in21 = new DataInputStream(socketCliente21.getInputStream()); 
			in21.read(mensaje_bytes); // se lee del stream de entrada el mensaje de bienvenida del servidor FTP
			System.out.println(new String(mensaje_bytes)); // se imprime en pantalla dicho mensaje
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// creacion de la instruccion para mandar el login de usuario al servidor FTP
		String usuarioString = "USER " + DEFAULT_USERNAME + '\n';
		mensaje_bytes = new byte[2048]; // creacion de buffer limpio
		mensaje_bytes = usuarioString.getBytes(); // se convierte la intruccion de login en bytes dentro del buffer
		try {
			// se manda la instruccion en bytes al stream asociado al socket para que llegue al servidor
			out21.write(mensaje_bytes); 
			mensaje_bytes = new byte[2048]; // creacion de buffer limpio
			// se lee la respuesta del servidor FTP desde el stream de entrada asociada al socket
			in21.read(mensaje_bytes);
			System.out.println(new String(mensaje_bytes)); // se imprime en pantalla la respuesta
		} catch (IOException e) {
			e.printStackTrace();
		}

		// creacion de la instruccion para mandar el login de contraseña al servidor FTP
		String contrasenaString = "PASS " + DEFAULT_PASSWORD + '\n';
		mensaje_bytes = new byte[2048]; // creacion de buffer limpio
		mensaje_bytes = contrasenaString.getBytes(); // se convierte la intruccion de login en bytes dentro del buffer
		try {
			// se manda la instruccion en bytes al stream asociado al socket para que llegue al servidor
			out21.write(mensaje_bytes); 
			mensaje_bytes = new byte[2048]; // creacion de buffer limpio
			// se lee la respuesta del servidor FTP desde el stream de entrada asociada al socket
			in21.read(mensaje_bytes);
			System.out.println(new String(mensaje_bytes)); // se imprime en pantalla la respuesta
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// creacion de la instruccion para realizar la comunicacion en modo pasivo con el servidor FTP
		String modo = new String("PASV\n");
		mensaje_bytes = new byte[2048]; // creacion de buffer limpio
		mensaje_bytes = modo.getBytes(); // se convierte la intruccion de modo pasivo en bytes dentro del buffer
		try {
			// se manda la instruccion en bytes al stream asociado al socket para que llegue al servidor
			out21.write(mensaje_bytes);
			mensaje_bytes = new byte[2048]; // creacion de buffer limpio
			// se lee la respuesta del servidor FTP desde el stream de entrada asociada al socket
			in21.read(mensaje_bytes);
			// se introduce la respuesta en el string para poder manipularlo posteriormente
			puertoPasivoString = new String(mensaje_bytes); // se imprime en pantalla la respuesta
			System.out.println(puertoPasivoString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// se obtienen las partes de la respuesta del modo pasivo "cortando" el string segun el separador coma (,)
		String []a = puertoPasivoString.split(",");
		String n1 = a[a.length-2].trim(); // se obtiene el penultimo string
		
		// se obtiene el ultimo string, lo "cortamos" segun el separador contrabarra (\)
		String []b = a[a.length-1].trim().split("\\)"); 
		String n2 = b[0].trim(); // se obtiene el primer string
		
		// las operaciones anteriores nos permiten calcular el puerto mediante la operacion siguiente
		int puertoPasivoInt = (Integer.parseInt(n1) * 256) + Integer.parseInt(n2);
		// se imprime el puerto ya calculado a modo de informacion adicional
		System.out.println("Puerto pasivo de esucha: " + puertoPasivoInt + '\n');

		// creacion de la instruccion para listar el directorio actual en el servidor
		String listar = new String("LIST\n");
		mensaje_bytes = new byte[2048]; // creacion de buffer limpio
		mensaje_bytes = listar.getBytes(); // se convierte la intruccion de listar en bytes dentro del buffer
		try {
			// creacion del socket cliente con el servidor introducido desde consola por el puerto de datos
			// calculado en tiempo de ejecucion anteriormente al activar el modo pasivo en el servidor
			socketClienteDataPort = new Socket(InetAddress.getByName(servidorString.trim()), puertoPasivoInt);
			// instanciacion y asignacion del stream de entrada con el puerto de datos
			inDataPort = new DataInputStream(socketClienteDataPort.getInputStream());
			// se manda la instruccion en bytes al stream asociado al socket para que llegue al servidor
			out21.write(mensaje_bytes);
			mensaje_bytes = new byte[2048];  // creacion de buffer limpio
			// se lee la respuesta del servidor FTP desde el stream de entrada asociada al socket de datos
			inDataPort.read(mensaje_bytes); // se imprime en pantalla la respuesta
			System.out.println(new String(mensaje_bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
