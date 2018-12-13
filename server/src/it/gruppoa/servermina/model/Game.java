package it.gruppoa.servermina.model;

/**
 * Classe che rappresenta la partita in se
 * @author alecsferra
 */
public class Game{
    
    //Stato della partita
    private GameState gameState;
    //Tabella
    private final Table table;
    
    /**
     * Creazioe di una partita data altezza e larghezza
     * @param width larghezza
     * @param height altezza
     */
    public Game(int width, int height){
        
        this(new Table(width, height, height * width / 4));
        
    }
    
    /**
     * Creazioe di una partita data una tabella
     * @param table tabella
     */
    public Game(Table table){
        
        this.table = table;
        this.gameState = GameState.RUNNING;
    }
    
    /**
     * Scopri una cella e aggiorna il game state
     * @param x coordinata x (0 based)
     * @param y coordinata y (0 based)
     * @return Messaggio di scoperta
     */
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

    /**
     * Ottieni il game state attuale
     * @return il game state
     */
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public String toString() {
        return this.table.toString();
    }
    
    
    
}