package it.gruppoa.clientmina;

import java.util.Scanner;

/**
 * Classe di utility
 * @author alecsferra
 */
public class Util {
    
    /**
     * Legge tutto il buffer di uno scanner ch usa "\0" come terminator
     * @param s scanner
     * @return tutte le stringhe nel buffer
     */
    public static String readWholeBuffer(Scanner s){
        
        StringBuilder sbld = new StringBuilder();
        
        String line = "0xCOFFEBABE";
        while(!"\0".equals(line)){
            line = s.nextLine();
            //System.err.print(line);
            sbld.append(line).append("\n");
        }
        //System.out.println("Gonna");
        return sbld.substring(0, sbld.length() - 2);
        
    }
    
}
