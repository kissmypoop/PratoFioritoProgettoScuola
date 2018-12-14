package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.Pair;
import it.gruppoa.servermina.model.util.WorldRandom;

/**
 * Classe utiliy per genrare "tablle gemelle" cioè della stessa grandezza e con
 * un numero di bombe bivise tra loro
 *
 * @author st13299
 */
public class TableFactory {

    /**
     * Ottieni un paio di tabelle gemelle (Vedi descrizione della classe)
     *
     * @param width larghezza
     * @param height altezza
     * @param nTotalBombs numero di bombe da mettere su il campo
     * @return la coppia di tabelle all' interno di un Pair
     */
    public static Pair<Table, Table> createTwinTables(int width, int height, int nTotalBombs) {

        if (nTotalBombs > (4f / 5f) * (width * height * 2) || nTotalBombs < (1f / 5f) * (width * height * 2)) {
            throw new IllegalArgumentException("Il numero di bombe è maggiore o minore di 1/5 o 4/5");
        }

        int min = width * height * 1 / 5;
        int max = width * height * 4 / 5;
        int rand = WorldRandom.randomBetween(Math.max(nTotalBombs - max, min), Math.min(nTotalBombs - min, max));
        return Pair.of(new Table(width, height, rand),
                new Table(width, height, nTotalBombs - rand));

    }

}
