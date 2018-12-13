package it.gruppoa.servermina.model;

/**
 * Classe che rappresenta lo stato di una partita
 * @author alecsferra
 */
public enum GameState{
    
    //La partita è in corso
    RUNNING(""),
    //La partita è stata vinta
    WON("Congratulazioni hai vinto!"),
    //Lapartita è stata persa
    LOST("Ritenta, sarai più fortunato");
    
    //Messaggio di fine partita
    private final String message;

    private GameState(String message) {
        this.message = message;
    }

    //Ottieni il messaggio di partita
    public String getMessage() {
        return message;
    }
    
}
