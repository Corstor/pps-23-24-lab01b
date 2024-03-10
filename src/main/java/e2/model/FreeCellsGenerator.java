package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import e2.Pair;

public class FreeCellsGenerator implements CellsGenerator<Cell> {

    @Override
    public List<Cell> generateCells(int size, Predicate<Cell> filter) {
        List<Cell> cells = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var position = new Pair<Integer,Integer>(i, j);
                var newCell = new CellImpl(position);
                if (filter.test(newCell)) {
                    cells.add(newCell);
                }
            }
        }
        return cells;
    }

}
