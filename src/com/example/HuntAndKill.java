package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HuntAndKill implements AlgorithmIf {

    public Grid on(Grid grid) {
        Cell current = Grid.getRandomElement(grid.getAllCells());

        Cell neighbor = null;
        while (current != null) {
            List<Cell> unvisited_neighbors = current.neighbors(grid).stream()
                    .filter(Cell::isEmptyLinks).collect(Collectors.toList());
            if (unvisited_neighbors.size() > 0) {
                neighbor = Grid.getRandomElement(unvisited_neighbors);
                current.link(neighbor, true);
                current = neighbor;
            } else {
                current = null;
                Boolean breaker = false;
                for (int i = 0; i < grid.rows; i++) {
                    for (int j = 0; j < grid.cols; j++) {
                        Cell cell = grid.getCell(i, j);
                        List<Cell> visited_neighbors = cell.neighbors(grid).stream()
                                .filter(Cell::hasLinks).collect(Collectors.toList());
                        if (cell.isEmptyLinks() && visited_neighbors.size() > 0) {
                            current = cell;
                            neighbor = Grid.getRandomElement(visited_neighbors);
                            current.link(neighbor, true);
                            breaker = true;
                            break;
                        }
                    }
                    if (breaker) {
                        break;
                    }
                }
            }
        }
        return grid;
    }

    @Override
    public String getName() {
        return "HuntAndKill";
    }
}
