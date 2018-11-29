/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

/**
 *
 * @author st13299
 */
public enum Cell {

    BOMB('O'),
    FLOWER('*');

    private final char rappresentation;
    private boolean discovered;

    private Cell(char rappresentation) {
        this.rappresentation = rappresentation;
        this.discovered = false;
    }

    @Override
    public String toString() {
        return discovered ? String.valueOf(rappresentation) : " ";
    }

    public CellDiscoverResult discover() {

        this.discovered = true;

        if (this.rappresentation == '*') {
            return CellDiscoverResult.CLEAR;
        }

        return WorldRandom.RANDOM.nextInt(2) == 0
                ? CellDiscoverResult.BOMB_EXPLODED
                : CellDiscoverResult.BOMB_NOT_EXPLODED;

    }

    public boolean isDiscovered() {
        return discovered;
    }

}
