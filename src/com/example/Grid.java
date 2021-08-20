package com.example;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    int rows;
    int cols;
    private List<List<Cell>> grid;

    public Grid(int rows, int cols) {

       this.rows = rows;
       this.cols = cols;

        grid = prepareGrid();
        configureCells();
    }

    public List prepareGrid() {
        ArrayList rowArr = new ArrayList();


        for( int i = 0; i < rows; i++ ){
            ArrayList colArr = new ArrayList();
            rowArr.add(colArr);
            for(int j = 0; j < cols; j++){
                Cell c = new Cell(i, j);
                colArr.add(c);
            }
        }
        return rowArr;
    }

    public Cell getCell(int row, int col){
        if (row > rows - 1 || col > cols - 1 || row < 0 || col < 0){
            return null;
        }
        return grid.get(row).get(col);
    }

    private int getSize(){
        return rows * cols;
    }

    public void configureCells() {
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                Cell cell = getCell(i,j);
                int r = cell.row;
                int c = cell.col;
                cell.north = getCell(r-1, c);
                cell.south = getCell(r+1, c);
                cell.west = getCell(r,c-1);
                cell.east = getCell(r, c+1);
            }
        }
    }

    public Cell randomCell() {
        int randRow = (int) Math.floor(Math.random() * rows);
        int randCol = (int) Math.floor(Math.random() * cols);
        return getCell(randRow, randCol);
    }

    private String wallModifier(int count){
        String output = "---+";
        String s = "";
        for(int i = 0; i < count; i++){
            s += output;
        }
        return s;
    }

    public void drawASCII(){
        String output = "+" + wallModifier(cols) + "\n";
        for (int i = 0; i < rows; i++) {

            String top = "|";
            String bottom = "+";

            for (int j = 0; j < cols; j++) {

                Cell cell = getCell(i,j);
                if (cell == null){
                    cell = new Cell(-1,-1);
                }
                String body = "   ";

                String eastBoundary = " ";
                if ( !cell.isLinked(cell.east)){
                    eastBoundary = "|";
                }
                top += body + eastBoundary;

                String southBoundary = "   ";
                if ( !cell.isLinked(cell.south)){
                    southBoundary = "___";
                }

                String corner = "+";
                bottom += southBoundary + corner;
            }
            output += top + "\n";
            output += bottom + "\n";

        }
        System.out.print(output);
    }


}






