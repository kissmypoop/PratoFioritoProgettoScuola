/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.clientmina;

import java.util.Scanner;

/**
 *
 * @author aferr
 */
public class Util {
    
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
