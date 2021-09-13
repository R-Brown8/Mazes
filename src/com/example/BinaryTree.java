package com.example;


import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

//    private Grid grid;

//    public BinaryTree(int rows, int cols) {
//        grid = new Grid(rows, cols);
//
//
//    }

    public static Grid on(Grid grid) {
        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.cols; j++) {
                List<Cell> neighbors = new ArrayList<Cell>();
                Cell cell = grid.getCell(i, j);
                if (cell.north != null) {
                    neighbors.add(cell.north);
                }
                if (cell.east != null) {
                    neighbors.add(cell.east);
                }
                int index = (int) (Math.random() * (neighbors.size()));
                if (neighbors.size() > 0) {
                    Cell neighbor = neighbors.get(index);
                    if (neighbor != null) {
                        cell.link(neighbor, true);
                    }
                }

            }
        }

        return grid;
    }
}
