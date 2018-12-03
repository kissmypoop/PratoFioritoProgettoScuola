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
public enum GameState{
    
    RUNNING(""),
    WON("Congratulazioni hai vinto!"),
    LOST("Ritenta sarai più fortunato");
    
    private final String message;

    private GameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
