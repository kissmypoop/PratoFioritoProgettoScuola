/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.Pair;
import it.gruppoa.servermina.model.util.WorldRandom;

/**
 *
 * @author st13299
 */
public class TableFactory {

    public static Pair<Table, Table> createTwinTables(int width, int height, int nTotalBombs) {

        int totalCells = width * height * 2;
        if(nTotalBombs > (4f/5) * totalCells || nTotalBombs < (1f/5) * totalCells)
            throw new IllegalArgumentException("Il numero di bombe è maggiore o minore di 1/4 o 1/5");
        
        int bombsLeft = WorldRandom.RANDOM.nextInt(nTotalBombs + 1);

        return Pair.of(new Table(width, height, bombsLeft),
                       new Table(width, height, nTotalBombs - bombsLeft));

    }

}
