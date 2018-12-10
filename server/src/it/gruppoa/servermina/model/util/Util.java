/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model.util;

import java.util.Scanner;

/**
 *
 * @author st13299
 */
public class Util {
    
    public static void clearBuffer(Scanner s){
        
        String line = "0xCOFFEBABE";
        while(!"\0".equals(line)){
            line = s.nextLine();
            //System.err.print(line);
        }
        s.nextLine();
    }
    
}
