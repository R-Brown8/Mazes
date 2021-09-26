package com.example;

import java.util.ArrayList;
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

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        links = new HashMap();
    }

    public ArrayList<Cell> neighbors(Grid grid) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        Cell above = grid.getCell(row - 1, col);
        Cell below = grid.getCell(row + 1, col);
        Cell right = grid.getCell(row, col + 1);
        Cell left = grid.getCell(row, col - 1);

        if (above != null) {
            neighbors.add(above);
        }
        if (below != null) {
            neighbors.add(below);
        }
        if (right != null) {
            neighbors.add(right);
        }
        if (left != null) {
            neighbors.add(left);
        }

        return neighbors;
    }

    public Boolean hasLinks(){
        return !links.isEmpty();
    }

    public Boolean isEmptyLinks(){
        return links.isEmpty();
    }

    public Boolean isLinked(Cell cell) {
        if (links.get(cell) == null) {
            return false;
        }
        return links.get(cell);
    }

    public void link(Cell cell, Boolean bidi) {
        links.put(cell, true);
        if (bidi) {
            cell.link(this, false);
        }
    }

    public void unlink(Cell cell, Boolean bidi) {
        links.remove(cell);
        if (bidi) {
            cell.unlink(this, false);
        }
    }

    public Distances distances() {
        Distances distances = new
                Distances(this);
        ArrayList<Cell> frontier = new ArrayList<>();
        frontier.add(this);

        while (!frontier.isEmpty()) {
            ArrayList<Cell> new_frontier = new ArrayList<Cell>();
            for (Cell c : frontier) {
                for (Cell l : c.links.keySet()) {
                    if (distances.getDistance(l) == -1) {
                        distances.setDistance(l, distances.getDistance(c) + 1);
                        new_frontier.add(l);
                    }
                }
            }
            frontier = new_frontier;
        }
        return distances;
    }
}
