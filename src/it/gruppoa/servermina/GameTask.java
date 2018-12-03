/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina;

import it.gruppoa.servermina.model.Game;
import it.gruppoa.servermina.model.GameState;
import java.util.Scanner;

/**
 *
 * @author st13299
 */
public class GameTask extends Thread{

    private Game game;
    
    @Override
    public void run() {
        
        this.game = new Game(3, 3);
        
        Scanner cin = new Scanner(System.in);
        
        while(this.game.getGameState() == GameState.RUNNING){
            
            System.out.println(game);
            
            System.out.println(game.discover(cin.nextInt(), cin.nextInt()));
                   
        }
        
        System.out.println(game);
        
        System.out.println(game.getGameState().getMessage());
        
    }
    
}
