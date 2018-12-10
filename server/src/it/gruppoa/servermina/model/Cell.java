/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.WorldRandom;

/**
 *
 * @author st13299
 */
public class Cell {

    private final CellType type;
    private boolean discovered;

    public Cell(CellType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return discovered ? String.valueOf(type.getRappresentation()) : "X";
    }

    public CellDiscoverResult discover() {

        this.discovered = true;

        if (this.type != CellType.BOMB) {
            return CellDiscoverResult.CLEAR;
        }

        return WorldRandom.RANDOM.nextInt(2) == 0
                ? CellDiscoverResult.BOMB_EXPLODED
                : CellDiscoverResult.BOMB_NOT_EXPLODED;

    }

    public boolean isDiscovered() {
        return discovered;
    }
    
    public boolean isBomb(){
        
        return this.type == CellType.BOMB;
        
    }
    
    public enum CellType{
        
        BOMB('O'),
        FLOWER('*');
        
        private final char rappresentation;

        private CellType(char rappresentation) {
            this.rappresentation = rappresentation;
        }

        public char getRappresentation() {
            return rappresentation;
        }
        
    }

}
