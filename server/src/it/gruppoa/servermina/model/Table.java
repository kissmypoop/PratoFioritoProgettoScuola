/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.WorldRandom;
import java.util.Arrays;

/**
 *
 * @author st13299
 */
public class Table {

    private final Cell[][] data;

    public Table(int width, int height, int nBombs) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Lunghezza e largezza devono essere maggiori di 0");
        }
        
        if(nBombs < 1 || nBombs > width * height)
            throw new IllegalArgumentException("Invalid bomb number");

        this.data = new Cell[width][height];

        while(nBombs > 0){
            
            int randX = WorldRandom.RANDOM.nextInt(width);
            int randY = WorldRandom.RANDOM.nextInt(height);
            
            if(data[randX][randY] == null){
                data[randX][randY] = new Cell(Cell.CellType.BOMB);
                nBombs--;
            }
            
        }
        
        for(Cell[] row : data)
            for(int i = 0; i < row.length; i++)
              if(row[i] == null)
                  row[i] = new Cell(Cell.CellType.FLOWER);

    }

    @Override
    public String toString() {

        StringBuilder bld = new StringBuilder();

        for (Cell[] row : this.data) {

            for (Cell c : row) {
                bld.append(c.toString()).append(" ");
            }

            bld.append("\n");

        }
        return bld.toString();

    }

    public CellDiscoverResult discover(int x, int y) {

        if (x >= data.length || y >= data[0].length) {
            throw new IllegalArgumentException("X e Y devono essere minori della lunghezza/larghezza");
        }

        return this.data[x][y].discover();

    }

    public long numberOfBombsLeft() {

        return Arrays.stream(this.data)
                .flatMap(Arrays::stream)
                .filter(x -> x.isBomb() && !x.isDiscovered())
                .count();

    }

    public boolean isAnyFlowerLeft() {

        return !Arrays.stream(this.data)
                .flatMap(Arrays::stream)
                .filter(x -> !x.isBomb())
                .allMatch(Cell::isDiscovered);

    }

}
