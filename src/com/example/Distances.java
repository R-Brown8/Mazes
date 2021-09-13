package com.example;

import java.util.*;

public class Distances {

    Cell root;
    HashMap<Cell, Integer> cellsMap = new HashMap();

    public Distances(Cell root) {
        this.root = root;
        cellsMap.put(root, 0);
    }

    public Distances path_to(Cell goal) {
        Cell current = goal;
        Distances breadcrumbs = new Distances(root);
        breadcrumbs.setDistance(current, getDistance(current));

        while (current != root) {
            for (Cell neighbor : current.links.keySet()) {
                if (getDistance(neighbor) < getDistance(current)) {
                    breadcrumbs.setDistance(neighbor, getDistance(neighbor));
                    current = neighbor;
                    break;
                }
            }
        }

        return breadcrumbs;
    }

    public Object[] max() {
        int max_distance = 0;
        Cell max_cell = root;

        for (Map.Entry<Cell, Integer> entry : cellsMap.entrySet()) {
            Cell cell = entry.getKey();
            Integer distance = entry.getValue();

            if (distance > max_distance) {
                max_cell = cell;
                max_distance = distance;
            }
        }
        Object[] obj = new Object[]{max_cell,max_distance};
        return obj;
    }

    public int getDistance(Cell cell) {
        if (!cellsMap.containsKey(cell)) {
            return -1;
        }
        return cellsMap.get(cell);
    }

    public void setDistance(Cell cell, int distance) {
        cellsMap.put(cell, distance);
    }

    public Cell[] getCells() {
        return (Cell[]) cellsMap.keySet().toArray();
    }


}
