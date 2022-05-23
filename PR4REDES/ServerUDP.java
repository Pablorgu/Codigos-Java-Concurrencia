package P4;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
         int port = 54322; // puerto del servidor
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        //int port = Integer.parseInt(args[0]); // puerto del servidor

        // SOCKET
        DatagramSocket server = null;

        //* COMPLETAR Crear e inicalizar el socket del servidor
        try{
        server = new DatagramSocket(port);
        }catch(  SocketException e){

        }

        
        // Funcion PRINCIPAL del servidor
        while (true)
        {
            //* COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta de máximo 500 bytes
            DatagramPacket recibir = new DatagramPacket(new byte[500], 500);
            //* COMPLETAR: Recibir datagrama
            try{
                server.receive(recibir);
            }catch(IOException e){

            }
            //* COMPLETAR: Obtener texto recibido
            String line = null;
            try{
                line = new String(recibir.getData(),recibir.getOffset(), recibir.getLength(), "UTF-8");
            }catch(UnsupportedEncodingException e){

            }
            //* COMPLETAR: Mostrar por pantalla la direccion socket (IP y puerto) del cliente y su texto
            System.out.printf("Dirección socket:" + recibir.getAddress() + " con puerto: " + recibir.getPort() + " y mensaje: " + line);
            // Capitalizamos la linea
            line = capitalize(line);            
            //* COMPLETAR: crear datagrama de respuesta
            DatagramPacket respuesta = new DatagramPacket(line.getBytes(), line.length(), recibir.getAddress(), recibir.getPort());
            //* COMPLETAR: Enviar datagrama de respuesta
            try{
                server.send(respuesta);
            }catch(IOException e){

            }
        } // Fin del bucle del servicio
    }

}
