/**
 *
 * @author <tu nombre aqui>
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.sql.rowset.spi.SyncResolver;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        // DATOS DEL SERVIDOR:
        //* FIJOS: coméntelos si los lee de la línea de comandos
        // String serverName = "127.0.0.1"; // direccion local
        // int serverPort = 12345;
        //* VARIABLES: descoméntelos si los lee de la línea de comandos
        String serverName = args[0];
        int serverPort = Integer.parseInt(args[1]);

        // SOCKET
        Socket serviceSocket = null;

        // FLUJOS PARA EL ENVÍO Y RECEPCIÓN
        PrintWriter out = null;
        BufferedReader in = null;

        //* COMPLETAR: Crear socket y conectar con servidor
        try{
            serviceSocket = new Socket(serverName, serverPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //* COMPLETAR: Inicializar los flujos de entrada/salida del socket conectado en las variables PrintWriter y BufferedReader
        try{
            out =new PrintWriter(serviceSocket.getOutputStream(), true);
            in = new BufferedReader((new InputStreamReader(serviceSocket.getInputStream())));
            } catch (Exception e){
                e.printStackTrace();
            }
        }   
        //* COMPLETAR: Recibir mensaje de bienvenida del servidor y mostrarlo
        System.out.println("Status. Conectado al servidor");
        // Obtener texto por teclado
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Introduzca un texto a enviar (TERMINAR para acabar)");
        userInput = stdIn.readLine();

        //* COMPLETAR: Comprobar si el usuario ha iniciado el fin de la interacción
        while (userInput.compareTo("") != 0) { // bucle del servicio
            //* COMPLETAR: Enviar texto en userInput al servidor a través del flujo de salida del socket conectado
            out.print(userInput);
            //* COMPLETAR: Recibir texto enviado por el servidor a través del flujo de entrada del socket conectado
            String line = null;
            line = in.readLine();

            // Leer texto de usuario por teclado
            System.out.println("Introduzca un texto a enviar (TERMINAR para acabar)");
            userInput = stdIn.readLine();
        } // Fin del bucle de servicio en cliente

        // Salimos porque el cliente quiere terminar la interaccion, ha introducido TERMINAR
        //* COMPLETAR: Enviar END al servidor para indicar el fin deL Servicio

        //* COMPLETAR: Recibir el OK del Servidor

        //* COMPLETAR Cerrar flujos y socket
    }
}
