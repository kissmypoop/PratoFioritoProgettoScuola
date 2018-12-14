package it.gruppoa.clientmina;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alecsferra
 */
public class Main {

    //Segnale di fine messaggio
    private static final String MESSAGE_TERMINATOR = "\n\0\n";

    public static void main(String[] args) {
        
        if(args.length != 2){
            System.err.println("Errore nell utilizzo: \"java -jar ClientMina <indirizzo-server> <porta>\"");
            System.exit(-1);
        }

        try (Socket conn = new Socket(args[0], Integer.parseInt(args[1]));
                PrintWriter sin = new PrintWriter(conn.getOutputStream(), true);
                Scanner sout = new Scanner(conn.getInputStream());
                Scanner cin = new Scanner(System.in)) {

            String msg;

            do {

                //System.out.println("Mossa");
                msg = Util.readWholeBuffer(sout);
                System.out.print(msg);
                String[] splitMsg = msg.split("\n");
                if (splitMsg.length > 1 && splitMsg[1].startsWith("Fine")) {
                    break;
                }

                sin.println(cin.nextLine());
                System.out.println();
                sin.println(MESSAGE_TERMINATOR);

            } while (true);

        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e);
        } catch(NumberFormatException e){
            System.err.println("Errore nell utilizzo: \"java -jar ClientMina <indirizzo-server> <porta>\"");
            System.exit(-1);
        }

    }

}
