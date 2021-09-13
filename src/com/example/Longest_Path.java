package com.example;

public class Longest_Path {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid = Sidewinder.on(grid);
//        grid.drawASCII();

        Cell start = grid.getCell(0, 0);
        Distances distances = start.distances();

        Object[] info = distances.max();
        Cell new_start = (Cell) info[0];
        int distance = (int) info[1];

        Distances new_distances = new_start.distances();

        Object[] info2 = new_distances.max();
        Cell goal = (Cell) info2[0];
        distance = (int) info2[1];
        distances = new_distances.path_to(goal);
        grid.to_png(100, distances);

    }
}
