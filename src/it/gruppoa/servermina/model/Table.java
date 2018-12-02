/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model;

/**
 *
 * @author st13299
 */
public class Table {
    
    private final Cell[][] data;

    public Table(int width, int leght) {
        
        this.data = new Cell[width][leght];
        
        for(Cell[] row : this.data)
            for(int i = 0; i < row.length; i++)
                row[i] = new Cell(Cell.CellType.BOMB);
        
    }

    @Override
    public String toString() {
    
       StringBuilder bld = new StringBuilder();
       
       return bld.toString();
       
    }
    
}
