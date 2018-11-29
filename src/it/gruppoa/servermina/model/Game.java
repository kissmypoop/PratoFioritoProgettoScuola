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
public class Game {
    
    private GameState gameState;
    private final Table table;
    
    public Game(int width, int height){
        
        this.table = new Table(width, height);
        
    }
    
}