package com.example;

import java.util.List;
import java.util.Map;

public class Mazes {
    public static void main(String[] args) {
        Grid grid = new Grid(5, 5);
//          grid = HuntAndKill.on(grid);
//        grid = Wilsons.on(grid);
//        grid = AldousBroder.on(grid);
//        grid = Sidewinder.on(grid);
//        grid.drawASCII();

        Cell root = grid.getCell(0, 4);
        Distances distances = root.distances();
        distances = distances.path_to(grid.getCell(4, 0));
        List<Cell> deadends = grid.deadends();
        System.out.println("Deadend count:" + deadends.size());
        grid.to_png(100, distances);
        
    }


}
