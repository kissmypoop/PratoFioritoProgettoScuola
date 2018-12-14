package it.gruppoa.servermina.model.util;

import java.util.Random;

/**
 * Classe di utility per i numeri casuali
 * @author alecsferra
 */
public class WorldRandom {
    
    //Unica istanza di random globale
    public final static Random RANDOM = new Random();
    
    /**
     * Ottieni un booleano in base alla percentale di uscita
     * @param per percentaul di uscita di true
     * @return il boolano uscito
     */
    public static boolean getBoolFromPerc(int per){
        
        if(per < 0 || per > 100)
            throw new IllegalArgumentException("Percentuale non valida");
        
        return RANDOM.nextInt(101) <= per;
    
    }
    
    /**
     * Ottinei un numero casueal nell' intervallo
     * @param min valoreminimo
     * @param max valore massimo
     * @return ilnumero casuale
     */
    public static int randomBetween(int min, int max){
        
        if(min > max)
            throw new IllegalArgumentException("Il minimo non può esseremaggiore del massimo");
        
        return RANDOM.nextInt((max - min) + 1) + min;
        
    }
    
}
