package it.gruppoa.servermina.model;

/**
 * Classe che rappresenta il risultato dlla scoperta di una cella
 * @author alecsferra
 */
public enum CellDiscoverResult{
    
    //La cella era una bomba ed è esplosa
    BOMB_EXPLODED("Oh che sfortuna! Hai trovato una bomba ed è esplosa"),
    //La cella era una bomba ma non è esplosa
    BOMB_NOT_EXPLODED("Sei stato molto fortunato! Hai trovato una bomba ma non è esplosa"),
    //La cella era un fiore
    CLEAR("Via libera");
    
    //Messaggio di scoperta
    private final String message;

    private CellDiscoverResult(String message) {
        this.message = message;
    }

    /**
     * Ottieni il messaggio
     * @return il messaggio
     */
    public String getMessage() {
        return message;
    }
    
}
