package e2.model;

import java.util.List;
import java.util.function.Predicate;

import e2.Pair;


public class GridImpl implements Grid {

    private final List<Cell> mines;
    private final List<Cell> freeCells;
    private final int size;

    public GridImpl(int size, int numberOfMines, CellsGenerator<Integer> cellsGenerator, CellsGenerator<Cell> freeCellsGenerator) {
        this.mines = cellsGenerator.generateCells(size, (index) -> index < numberOfMines);
        Predicate<Cell> filter = (cell) -> this.cellIsMine(cell);
        var freeCellsWithoutNeighborMines = freeCellsGenerator.generateCells(size, filter.negate());
        var neighborMinesSetter = new NeighborMinesSetterImpl(size);
        this.freeCells = neighborMinesSetter.set(freeCellsWithoutNeighborMines, filter);
        this.size = size;
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

    @Override
    public Cell getCell(Pair<Integer, Integer> position) {
        Cell cell = new CellImpl(position);

        if (checkBounds(position)) {
            throw new IllegalArgumentException("Tried to get a cell out of the bounds of the grid");
        }

        cell = findCell(cell, this.freeCells);

        return findCell(cell, this.mines);
    }

    private boolean checkBounds(Pair<Integer, Integer> position) {
        return position.getX() < 0 || position.getX() >= this.size || position.getY() < 0 || position.getY() >= this.size;
    }

    private Cell findCell(Cell cell, List<Cell> cells) {
        for (var foundCell : cells) {
            if (foundCell.getPosition().equals(cell.getPosition())) {
                return foundCell;
            }
        };
        return cell;
    }
}
