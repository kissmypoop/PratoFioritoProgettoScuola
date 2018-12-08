/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.clientmina;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aferr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private static final String messageTerminator = "\n\0\n";
    
    public static void main(String[] args) {

        try (Socket conn = new Socket("127.0.0.1", 1555);
             PrintWriter sin = new PrintWriter(conn.getOutputStream(), true);
             Scanner sout = new Scanner(conn.getInputStream());
             Scanner cin = new Scanner(System.in)
            ){
            
            String msg;
            
            do{
                
                //System.out.println("Mossa");
                msg = Util.readWholeBuffer(sout);
                System.out.print(msg);
                sin.println(cin.nextLine());
                sin.println(messageTerminator);
                
            }while(!msg.startsWith("Fine partita:"));

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
