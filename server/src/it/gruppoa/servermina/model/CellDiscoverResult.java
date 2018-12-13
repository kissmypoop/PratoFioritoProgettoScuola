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
public enum CellDiscoverResult{
    
    BOMB_EXPLODED("Oh che sfortuna! Hai trovato una bomba ed è esplosa"),
    BOMB_NOT_EXPLODED("Sei stato molto fortunato! Hai trovato una bomba ma non è esplosa"),
    CLEAR("Via libera");
    
    private final String message;

    private CellDiscoverResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
