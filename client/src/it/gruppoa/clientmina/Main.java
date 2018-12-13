package it.gruppoa.clientmina;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alecsferra
 */
public class Main {

    //Segnale di fine messaggio
    private static final String MESSAGE_TERMINATOR = "\n\0\n";

    public static void main(String[] args) {

        try (Socket conn = new Socket("127.0.0.1", 1555);
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

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
