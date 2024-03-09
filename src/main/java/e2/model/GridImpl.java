package e2.model;

import java.util.LinkedList;
import java.util.List;

import e2.Pair;

public class GridImpl implements Grid {

    private final List<Cell> mines;
    private final List<Cell> freeCells;
    private final int size;

    //TODO() Strategy

    public GridImpl(int size, int numberOfMines, CellsGenerator cellsGenerator) {
        this.size = size;
        this.mines = cellsGenerator.generateCells(size, numberOfMines);
        this.freeCells = setFreeCellsNeighborMines(this.createFreeCells(size));
    }

    private List<Cell> setFreeCellsNeighborMines(List<Cell> cells) {
        cells.forEach( (cell) -> {
            var neighborCells = getNearCells(cell);
            neighborCells.forEach( (mine) -> {
                if (cellIsMine(mine)) {
                    cell.incrementNeighborsMines();
                }
            });
        });
        return cells;
    }

    private List<Cell> createFreeCells(int size) {
        List<Cell> cells = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var position = new Pair<Integer,Integer>(i, j);
                var newCell = new CellImpl(position);
                if (!cellIsMine(newCell)) {
                    cells.add(newCell);
                }
            }
        }
        return cells;
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
        return neighborPosition.getX() > 0 
            && neighborPosition.getY() > 0 
            && neighborPosition.getX() < this.size 
            && neighborPosition.getY() < this.size;
    }

    @Override
    public boolean cellIsMine(Cell newCell) {
        return this.mines.contains(newCell);
    }

    @Override
    public List<Cell> getMines() {
        return List.copyOf(this.mines);
    }

    @Override
    public List<Cell> getFreeCells() {
        return List.copyOf(this.freeCells);
    }
}
