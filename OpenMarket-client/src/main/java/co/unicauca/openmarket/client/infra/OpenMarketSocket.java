package co.unicauca.openmarket.client.infra;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naren Imbachi
 */
public class OpenMarketSocket {
    //Socket de la aplicacion cleinte
    private java.net.Socket socket = null;
    
    //Permite leer la respuesta del socket 
    private Scanner input;
    
    //Permite evniar una solicitud por el socket 
    private PrintStream output;
    
    //Ip del server Socket
    //EL ERROR SE SOLUCIONA AL AGREGAR EL COMMONS EN LAS DEPENDENCIA Y TOCA IMPORTAR
    //private final String IP_SEERVER = Utilities.loadProperty("server.ip");
    
    //Puerto del server Spcket 
    //private final int PORT = Integer.parseInt(Utilities.loadProperty("server.port"));

    /**
     * @brief envia una solicitud desde la aplicacion cliente al servidor por medio de socket
     * @param requestJson Solicitud en formato Json
     * @return Respuesta del socket 
     */
    public String sendRequest(String requestJson) throws IOException {
        String response = "";
        input = new Scanner(socket.getInputStream());
        output = new PrintStream(socket.getOutputStream());
        output.flush();
        input.reset();

        Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.INFO, "Lo que se le envia al: ("+requestJson+")");
           
        // Enviar la solicitud
        output.println(requestJson);

        // Procesa la respuesta
        if (input.hasNextLine()) {
            response = input.nextLine();
        }
        Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.INFO, "Lo que se lee del servidor: ("+response+")");
                
        return response;
    }
    
    /**
     * @brief Cierrra los flujos input y output
     */
    public void closeStream(){
        output.close();;
        input.close();;
    }
    
    /**
     * @brief Conectar socket
     */
    /*public void conect() throws IOException{
        socket = new java.net.Socket(IP_SERVER, PORT);
        Logger.getLogger("SocketClient").log(Level.INFO, "Socket establecido");
    }*/
    
    /**
     * @brief Desconectar el socket
     */
    public void disconnect(){
        closeStream();
        try {
            socket.close();
        } catch (Exception ex) {
            Logger.getLogger(OpenMarketSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
