package com.example;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    int row;
    int col;
    Cell north;
    Cell east;
    Cell south;
    Cell west;
    Map<Cell, Boolean> links;


    public Cell(int row, int col){
        this.row = row;
        this.col = col;

        links = new HashMap();
    }

    public Boolean isLinked(Cell cell){
        if (links.get(cell) == null){
            return false;
        }
        return links.get(cell);
    }

    public void link(Cell cell, Boolean bidi){
        links.put(cell,true);
        if (bidi) {
            cell.link(this, false);
        }
    }

    public void unlink(Cell cell, Boolean bidi){
        links.remove(cell);
        if(bidi){
            cell.unlink(this,false);
        }
    }
}
