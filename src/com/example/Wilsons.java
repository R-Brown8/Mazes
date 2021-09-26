package com.example;

import java.util.ArrayList;

public class Wilsons {

    public static Grid on(Grid grid) {
        ArrayList<Cell> unvisited = grid.getAllCells();
        Cell first = Grid.getRandomElement(unvisited);
        unvisited.remove(first);

        while (!unvisited.isEmpty()) {
            Cell cell = Grid.getRandomElement(unvisited);
            ArrayList<Cell> path = new ArrayList<Cell>();
            path.add(cell);

            while (unvisited.contains(cell)) {
                cell = Grid.getRandomElement(cell.neighbors(grid));
                Integer position = path.indexOf(cell);
                if (position != -1) {
                    path = new ArrayList<>(path.subList(0, position + 1));
                } else {
                    path.add(cell);
                }
            }
            for (int x = 0; x < path.size() - 2; x++) {
                path.get(x).link(path.get(x + 1), true);
                unvisited.remove(path.get(x));
            }
        }
        return grid;
    }


}
