package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.WorldRandom;

/**
 * Classe che rappresenta una cella
 * @author st13299
 */
public class Cell {

    //Tipo della cella
    private final CellType type;
    //booleano che tiene traccia se la cella è stata scoperta o meno
    private boolean discovered;

    //Costruttore principale dato il tipo
    public Cell(CellType type) {
        this.type = type;
    }

    /**
     * Rappresenta la cella come stringa
     * @return la rappresentazione del tipo se la cella è già statat scopoerta altrimenti uno spazio
     */
    @Override
    public String toString() {
        return discovered ? String.valueOf(type.getRappresentation()) : "X";
    }

    /**
     * Scopre la cella
     * @return Conseguenza della scoperta della cella
     */
    public CellDiscoverResult discover() {

        this.discovered = true;

        if (this.type != CellType.BOMB) {
            return CellDiscoverResult.CLEAR;
        }

        return WorldRandom.getBoolFromPerc(66)
                ? CellDiscoverResult.BOMB_EXPLODED
                : CellDiscoverResult.BOMB_NOT_EXPLODED;

    }

    /**
     * Controlla se la cella è stata scoperta
     * @return true s la cella è stata scoperta
     */
    public boolean isDiscovered() {
        return discovered;
    }
    
    /**
     * Controlla se la cella è una bomba
     * @return true se la cella è una bomba
     */
    public boolean isBomb(){
        
        return this.type == CellType.BOMB;
        
    }
    
    /**
     * Enum che rappresenta il tipo di cella
     */
    public enum CellType{
        
        //Tipo bomba
        BOMB('O'),
        //Tipo fiore
        FLOWER('*');
        
        //rappresntaazione della cella come carattere
        private final char rappresentation;

        private CellType(char rappresentation) {
            this.rappresentation = rappresentation;
        }

        /**
         * Ottieni la rappresentazione della cella
         * @return la rappresentazione
         */
        public char getRappresentation() {
            return rappresentation;
        }
        
    }

}
