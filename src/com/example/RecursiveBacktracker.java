package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecursiveBacktracker implements AlgorithmIf {

    public Grid on(Grid grid) {
        ArrayList<Cell> stack = new ArrayList();
        Cell startAt = Grid.getRandomElement(grid.getAllCells());
        stack.add(startAt);

        while(!stack.isEmpty()){
            Cell current = stack.get(stack.size()-1);
// probably not correct
            List<Cell> neighbors = current.neighbors(grid).stream()
                                        .filter(Cell::isEmptyLinks).collect(Collectors.toList()) ;  //ruby select = java stream filter
            if( neighbors.isEmpty()){
                stack.remove(stack.size()-1);
            }
            else {
                Cell neighbor = Grid.getRandomElement(neighbors);
                current.link(neighbor,true);
                stack.add(neighbor);
            }
        }
        return grid;
    }

    @Override
    public String getName() {
        return "RecursiveBacktracker";
    }
}
