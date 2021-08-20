package com.example;

public class Mazes {
    public static void main(String[] args){
        Grid grid = new Grid(9, 9);
        grid = BinaryTree.on(grid);
        grid.drawASCII();
    }
}
