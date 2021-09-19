package com.example;

import java.util.ArrayList;
import java.util.Random;

public class AldousBroder {

    public static Grid on(Grid grid) {
        Cell cell = grid.randomCell();
        Integer unvisited = (grid.size() - 1);
        while (unvisited > 0) {
            Cell neighbor = Grid.getRandomElement(cell.neighbors(grid));

            if (neighbor.links.isEmpty()) {
                cell.link(neighbor, true);
                unvisited -= 1;
            }
            cell = neighbor;
        }
        return grid;
    }

}
