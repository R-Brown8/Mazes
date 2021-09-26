package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Sidewinder implements AlgorithmIf{

    public Grid on(Grid grid) {

        for (int i = 0; i < grid.rows; i++) {
            ArrayList<Cell> run = new ArrayList<Cell>();
            for (int j = 0; j < grid.cols; j++) {
                Cell cell = grid.getCell(i, j);
                run.add(cell);
                boolean atEasternBoundary = (cell.east == null);
                boolean atNorthernBoundary = (cell.north == null);

                int random = (int) (Math.random() * 2);
                boolean shouldCloseOut = atEasternBoundary ||
                        (!atNorthernBoundary && random == 0);

                if (shouldCloseOut) {
                    Collections.shuffle(run);
                    Cell member = run.get(0);
                    if (member.north != null) {
                        member.link(member.north, true);
                    }
                    run.clear();
                } else {
                    cell.link(cell.east, true);
                }
            }
        }
        return grid;
    }

    @Override
    public String getName() {
        return "SideWinder";
    }
}
