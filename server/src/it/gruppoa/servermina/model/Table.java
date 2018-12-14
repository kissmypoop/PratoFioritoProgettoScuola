package it.gruppoa.servermina.model;

import it.gruppoa.servermina.model.util.WorldRandom;
import java.util.Arrays;

/**
 *CLass che rappresnta una tabella
 * @author alecsferra
 */
public class Table {

    //Matrice contenente le celle
    private final Cell[][] data;

    /**
     * Crea una tabella
     * @param width altezza
     * @param height larghzza
     * @param nBombs numero di bombe
     */
    public Table(int width, int height, int nBombs) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Lunghezza e largezza devono essere maggiori di 0");
        }
        
       if(nBombs < 0 || nBombs > width * height)
            throw new IllegalArgumentException("Numero di bombe NON valido");

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

    /**
     * Scopri una cella
     * @param x coordinata x (0 based)
     * @param y coordinata y (0 based)
     * @return il risultato della scoperta della cella
     */
    public CellDiscoverResult discover(int x, int y) {

        if (x >= data.length || y >= data[0].length) {
            throw new IllegalArgumentException("X e Y devono essere minori della lunghezza/larghezza");
        }

        if(data[x][y].isDiscovered())
            throw new IllegalArgumentException("Questa cella è già stata scoperta");
        
        return this.data[x][y].discover();

    }

    /**
     * Ottieni il numero di bombe sul campo non scoperet
     * @return numero di bomb sul campo non scoperte
     */
    public long numberOfBombsLeft() {

        return Arrays.stream(this.data)
                .parallel()
                .flatMap(Arrays::stream)
                .filter(x -> x.isBomb() && !x.isDiscovered())
                .count();

    }

    /**
     * Ottieni il numero di fiori non scoperti
     * @return il numero di fiori non scoperti
     */
    public boolean isAnyFlowerLeft() {

        return !Arrays.stream(this.data)
                .parallel()
                .flatMap(Arrays::stream)
                .filter(x -> !x.isBomb())
                .allMatch(Cell::isDiscovered);

    }

}
