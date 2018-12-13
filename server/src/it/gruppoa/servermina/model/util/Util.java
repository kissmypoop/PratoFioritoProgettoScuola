package it.gruppoa.servermina.model.util;

import java.util.Scanner;

/**
 * Raccolta di metodi di utility
 * @author alecsferra
 */
public class Util {
    
    /**
     * Pulisce il buffer di uno scanner che usa "\0" come terminator
     * @param s scanner
     */
    public static void clearBuffer(Scanner s){
        
        String line = "0xCOFFEBABE";
        while(!"\0".equals(line)){
            line = s.nextLine();
            //System.err.print(line);
        }
        s.nextLine();
    }
    
}
