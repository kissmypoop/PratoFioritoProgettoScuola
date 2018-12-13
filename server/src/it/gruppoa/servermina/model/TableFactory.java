package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.Pair;
import it.gruppoa.servermina.model.util.WorldRandom;

/**
 * Classe utiliy per genrare "tablle gemelle" cioè della stessa grandezza e con un numero di bombe bivise tra loro
 * @author st13299
 */
public class TableFactory {

    /**
     * Ottieni un paio di tabelle gemelle (Vedi descrizione della classe)
     * @param width larghezza
     * @param height altezza
     * @param nTotalBombs numero di bombe da mettere su il campo
     * @return la coppia di tabelle all' interno di un Pair
     */
    public static Pair<Table, Table> createTwinTables(int width, int height, int nTotalBombs) {

        int totalCells = width * height * 2;
        if(nTotalBombs > (4f/5) * totalCells || nTotalBombs < (1f/5) * totalCells)
            throw new IllegalArgumentException("Il numero di bombe è maggiore o minore di 1/4 o 1/5");
        
        int bombsLeft = WorldRandom.RANDOM.nextInt(nTotalBombs + 1);

        return Pair.of(new Table(width, height, bombsLeft),
                       new Table(width, height, nTotalBombs - bombsLeft));

    }

}
