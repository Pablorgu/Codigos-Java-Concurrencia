package es.uma.rysd2022.p4.vacios;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

/**
 *
 * @author <su nombre aquí>
 */
public class ServerUDP {
    public static String capitalize(String s){
        String words[] = s.split("\\s");
        String res = "";
        for(String w: words){
            if(!res.isEmpty()){
                res += " ";
            }
            res += w.substring(0,1).toUpperCase() + w.substring(1);
        }
        return res;
    }

    public static void main(String[] args)
    {
        // DATOS DEL SERVIDOR
        //* FIJO: Si se lee de línea de comando debe comentarse
        // int port = 54322; // puerto del servidor
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        int port = Integer.parseInt(args[0]); // puerto del servidor

        // SOCKET
        DatagramSocket server = null;

        //* COMPLETAR Crear e inicalizar el socket del servidor

        // Funcion PRINCIPAL del servidor
        while (true)
        {
            //* COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta de máximo 500 bytes

            //* COMPLETAR: Recibir datagrama

            //* COMPLETAR: Obtener texto recibido
            String line = null;

            //* COMPLETAR: Mostrar por pantalla la direccion socket (IP y puerto) del cliente y su texto

            // Capitalizamos la linea
            line = capitalize(line);

            //* COMPLETAR: crear datagrama de respuesta

            //* COMPLETAR: Enviar datagrama de respuesta

        } // Fin del bucle del servicio
    }

}
