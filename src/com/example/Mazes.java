package com.example;

import java.util.Map;

public class Mazes {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid = Sidewinder.on(grid);
//        grid.drawASCII();

        Cell root = grid.getCell(0, 9);
        Distances distances = root.distances();
        distances = distances.path_to(grid.getCell(9, 0));
        grid.to_png(100, distances);
        
    }


}
