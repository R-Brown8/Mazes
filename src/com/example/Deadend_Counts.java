package com.example;

import java.util.ArrayList;
import java.util.List;

public class Deadend_Counts {

    public static void main(String[] args) {
        ArrayList<AlgorithmIf> algorithms = new ArrayList<>();
        algorithms.add(new BinaryTree());
        algorithms.add(new Sidewinder());
        algorithms.add(new AldousBroder());
        algorithms.add(new HuntAndKill());
        for(AlgorithmIf a: algorithms) {
            Grid grid = new Grid(10, 10);
            a.on(grid);
            Cell root = grid.getCell(0, 9);
            Distances distances = root.distances();
            distances = distances.path_to(grid.getCell(9, 0));
            List<Cell> deadends = grid.deadends();
            System.out.println("Deadend count for " + a.getName() + ": " + deadends.size());
        }
    }

}
