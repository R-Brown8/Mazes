package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void to_png(int cellSize, Distances distances) {
        int img_width = cellSize * cols;
        int img_height = cellSize * rows;

        BufferedImage off_Image =
                new BufferedImage(img_width, img_height,
                        BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = off_Image.createGraphics();

        g2.setPaint(Color.white);
        g2.setStroke(new BasicStroke(0));
        g2.fillRect(0, 0, img_width, img_height);
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.black);

//        Cell distance color loop
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = getCell(i, j);
                Integer d = distances.getDistance(cell);
                int x1 = cell.col * cellSize;
                int y1 = cell.row * cellSize;
                int x2 = (cell.col + 1) * cellSize;
                int y2 = (cell.row + 1) * cellSize;

                if (d != -1) {
                    g2.setColor(Color.red);
                    g2.setStroke(new BasicStroke(0));
                    Color red = new Color(255, 0, 0);

                    red = new Color(255 - d*2, 0, 0);

                    g2.setPaint(red);
                    g2.setStroke(new BasicStroke(0));
                    g2.fillRect(x1, y1, (x2 - x1), (y2 - y1));
                    g2.setPaint(Color.white);
                }
            }

        }

//        Wall & Distance lable loop
        for (int i2 = 0; i2 < rows; i2++) {
                for (int j = 0; j < cols; j++) {

                    Cell cell = getCell(i2, j);
                    Integer d = distances.getDistance(cell);

                    int x1 = cell.col * cellSize;
                    int y1 = cell.row * cellSize;
                    int x2 = (cell.col + 1) * cellSize;
                    int y2 = (cell.row + 1) * cellSize;

                    g2.setStroke(new BasicStroke(4));
                    g2.setColor(Color.black);

                    if (cell.north == null) {
                        g2.drawLine(x1, y1, x2, y1);
                    }
                    if (cell.west == null) {
                        g2.drawLine(x1, y1, x1, y2);
                    }
                    if (!cell.isLinked(cell.east)) {
                        g2.drawLine(x2, y1, x2, y2);
                    }
                    if (!cell.isLinked(cell.south)) {
                        g2.drawLine(x1, y2, x2, y2);
                    }
                    if (d != -1) {
                        g2.drawString(String.valueOf(d), (x1 + cellSize / 2) - 4, (y1 + cellSize / 2) + 4);
                    }
                }
            }

        File outputfile = new File("saved.png");
        try {
            ImageIO.write(off_Image, "png", outputfile);
        } catch (IOException e) {

        }

    }

    public List prepareGrid() {
        ArrayList rowArr = new ArrayList();


        for (int i = 0; i < rows; i++) {
            ArrayList colArr = new ArrayList();
            rowArr.add(colArr);
            for (int j = 0; j < cols; j++) {
                Cell c = new Cell(i, j);
                colArr.add(c);
            }
        }
        return rowArr;
    }

    public static <T> T getRandomElement(ArrayList<T> list) {
        Random rand = new Random();
        int var = rand.nextInt(list.size());
        System.out.println(var);
         return list.get(var);
    }

    public ArrayList<Cell> getAllCells(){
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (List<Cell> row: grid){
            list.addAll(row);
        }
        return list;
    }

    public Cell getCell(int row, int col) {
        if (row > rows - 1 || col > cols - 1 || row < 0 || col < 0) {
            return null;
        }
        return grid.get(row).get(col);
    }

    private int getSize() {
        return rows * cols;
    }

    public void configureCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = getCell(i, j);
                int r = cell.row;
                int c = cell.col;
                cell.north = getCell(r - 1, c);
                cell.south = getCell(r + 1, c);
                cell.west = getCell(r, c - 1);
                cell.east = getCell(r, c + 1);
            }
        }
    }

    public Integer size() {
        return rows * cols;
    }

    public Cell randomCell() {
        int randRow = (int) Math.floor(Math.random() * rows);
        int randCol = (int) Math.floor(Math.random() * cols);
        return getCell(randRow, randCol);
    }

    private String wallModifier(int count) {
        String output = "---+";
        String s = "";
        for (int i = 0; i < count; i++) {
            s += output;
        }
        return s;
    }

    public void drawASCII() {
        String output = "+" + wallModifier(cols) + "\n";
        for (int i = 0; i < rows; i++) {

            String top = "|";
            String bottom = "+";

            for (int j = 0; j < cols; j++) {

                Cell cell = getCell(i, j);
                if (cell == null) {
                    cell = new Cell(-1, -1);
                }
                String body = "   ";

                String eastBoundary = " ";
                if (!cell.isLinked(cell.east)) {
                    eastBoundary = "|";
                }
                top += body + eastBoundary;

                String southBoundary = "   ";
                if (!cell.isLinked(cell.south)) {
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






