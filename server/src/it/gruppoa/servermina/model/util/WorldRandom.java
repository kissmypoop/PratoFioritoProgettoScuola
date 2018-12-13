/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model.util;

import java.util.Random;

/**
 *
 * @author st13299
 */
public class WorldRandom {
    
    public final static Random RANDOM = new Random();
    
    public static boolean getBoolFromPerc(int per){
        
        if(per < 0 || per > 100)
            throw new IllegalArgumentException("Percentuale non valida");
        
        return RANDOM.nextInt(101) <= per;
    
    }
    
}
