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
public class Game{
    
    private GameState gameState;
    private final Table table;
    
    public Game(int width, int height){
        
        this(new Table(width, height, 0));
        
    }
    
    public Game(Table table){
        
        this.table = table;
        this.gameState = GameState.RUNNING;
    }
    
    public String discover(int x, int y){
    
       if(x < 0 || y < 0)
            throw new IllegalArgumentException("X e Y devono essere maggiori di 0");
       
        CellDiscoverResult result = this.table.discover(x, y);
        
        if(result == CellDiscoverResult.BOMB_EXPLODED)
            this.gameState = GameState.LOST;
        
        if(!this.table.isAnyFlowerLeft())
            this.gameState = GameState.WON;
        
        return result.getMessage();
        
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public String toString() {
        return this.table.toString();
    }
    
    
    
}