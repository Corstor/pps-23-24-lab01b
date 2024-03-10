package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import e2.Pair;

public class NeighborMinesSetterImpl implements NeighborMinesSetter {

    private final int size;

    public NeighborMinesSetterImpl(int size) {
        this.size = size;
    }

    @Override
    public List<Cell> set(List<Cell> freeCells, Predicate<Cell> filter) {
        freeCells.forEach( (cell) -> {
            var neighborCells = getNearCells(cell);
            neighborCells.forEach( (mine) -> {
                if (filter.test(mine)) {
                    cell.incrementNeighborsMines();
                }
            });
        });
        return freeCells;
    }

    private List<Cell> getNearCells(Cell cell) {
        List<Cell> cells = new LinkedList<>();
        int adiacentPositionsOnSingleAxis = 3;
        for (int i = 0; i < adiacentPositionsOnSingleAxis; i++) {
            for (int j = 0; j < adiacentPositionsOnSingleAxis; j++) {
                var neighborCell = getNeighborCellFromIndexes(cell, i, j);
                if (checkPositionIsNotOutOfBound(neighborCell.getPosition()) && !cell.equals(neighborCell)) {
                    cells.add(neighborCell);
                }
            }
        }
        return cells;
    }

    private Cell getNeighborCellFromIndexes(Cell cell, int i, int j) {
        return new CellImpl(getNeighborPositionFromIndexes(cell.getPosition(), i, j));
    }

    private Pair<Integer, Integer> getNeighborPositionFromIndexes(Pair<Integer, Integer> position, int i, int j) {
        return new Pair<>(position.getX() + getOffsetFromIndex(i), position.getY() + getOffsetFromIndex(j));
    }

    private Integer getOffsetFromIndex(int index) {
        return index - 1;
    }

    private boolean checkPositionIsNotOutOfBound(Pair<Integer, Integer> neighborPosition) {
        return neighborPosition.getX() >= 0 
            && neighborPosition.getY() >= 0 
            && neighborPosition.getX() < this.size 
            && neighborPosition.getY() < this.size;
    }
}
