package it.gruppoa.servermina;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author alecsferra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length != 1){
            System.err.println("Errore nell utilizzo: \"java -jar ServerMina <porta>\"");
            System.exit(-1);
        }
        
        try(ServerSocket server = new ServerSocket(1555)){
            
            while(true){
                
                new GameTask(server.accept()).start();
                
            }
            
        } catch (IOException e) {
            System.err.println("Errore inerno al server: " + e);
        } catch(NumberFormatException e){
            System.err.println("Errore nell utilizzo: \"java -jar ServerMina <porta>\"");
            System.exit(-1);
        }
        
    }
    
}
