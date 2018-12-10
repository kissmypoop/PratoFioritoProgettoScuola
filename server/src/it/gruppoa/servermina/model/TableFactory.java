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

    public Pair<Table, Table> createTwinTables(int width, int height, int perc) {

        if (perc < 0 || perc > 100) {
            throw new IllegalArgumentException("Invalid percentage");
        }

        int nTotalBombs = width * height * perc / 100;
        int bombsLeft = WorldRandom.RANDOM.nextInt(nTotalBombs + 1);

        return Pair.of(new Table(width, height, bombsLeft),
                       new Table(width, height, nTotalBombs - bombsLeft));

    }

}
