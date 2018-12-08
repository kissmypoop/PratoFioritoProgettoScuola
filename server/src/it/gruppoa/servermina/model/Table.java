/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

import java.util.Arrays;

/**
 *
 * @author st13299
 */
public class Table {

    private final Cell[][] data;

    public Table(int width, int lenght) {

        if (width <= 0 || lenght <= 0) {
            throw new IllegalArgumentException("Lunghezza e largezza devono essere maggiori di 0");
        }

        this.data = new Cell[width][lenght];

        for (Cell[] row : this.data) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new Cell(WorldRandom.RANDOM.nextInt(2) == 0 ? Cell.CellType.FLOWER : Cell.CellType.BOMB);
            }
        }

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
